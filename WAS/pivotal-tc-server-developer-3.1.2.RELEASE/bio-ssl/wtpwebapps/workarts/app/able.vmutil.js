/**
 * Ablecoms VMUtil
 *
 * ViewModel Helper
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
(function() {

	/*****************************************************************************
	 * vmUtil - ViewModel Helpher Utility
	 *****************************************************************************/
	var VMUtil = function() {
		var self = this;
		self.clazz = "VMUtil";

		/*************************************************************************
		 * Common ViewModel Helper Functions
		 */
		/**
		 * jQuery Validate의 Message의 "__FIELD_NAME__"을 주어진 labelMap을 이용하여 변환한다.
		 *  설정방법: showErrors: self.createShowErrorFunctionWithLabelMap( lableMap )
		 *            - param lableMap: { 필드: "필드명", .... }
		 */
		self.createShowErrorFunctionWithLabelMap = function(labelMap) {
			var fieldNameCtx = (_.isEmpty(labelMap))? {} : labelMap; //클로져
			_.each(fieldNameCtx, function(value, key, ctx) {
				fieldNameCtx[key] = _.string.trim(value, " *:");
			});
			//jQuery Validate showError Function
			var showError = function(errorMap, errorList) {
				if(!_.isEmpty(errorMap)) {
					//errorMap: { "mst_code": "__FIELD_NAME__ 항목은 필수입니다.", ... }
					//errorList: [ {"message": "__FIELD_NAME__ 항목은 필수입니다.", "element":$(ctrl) }, ... ]
					//logToJson(errorMap, "showErrors - errorMap");
					//logToJson(_.map(errorList, function(item) { return { message: item.message, elementId: item.element.id }; }), "showErrors - errorList");

					//errorMap의 __FIELD_NAME__ 번역
					_.each(errorMap, function(value, key, list) {
						//labelMap 자동 구성 - cached at context
						if(!_.chain(fieldNameCtx).keys().contains(key).value()) {
							var labelText = $(document).find("label[for="+key+"]:first").text();
							fieldNameCtx[key] = _.string.trim(labelText, " *:");
						}
                        //filed id로 element의 data-label 추출
                        if(_.isEmpty(fieldNameCtx[key])) {
                            fieldNameCtx[key] = $("#"+key+"[data-label]").attr("data-label");
                        }
                        //filed name로 element의 data-label 추출
                        if(_.isEmpty(fieldNameCtx[key])) {
                            fieldNameCtx[key] = $("[name="+key+"]").attr("data-label");
                        }
						list[key] = value.replace(/__FIELD_NAME__/, fieldNameCtx[key] || key);
					});
					//logToJson(fieldNameCtx, "showErrors - fieldNameCtx");

					//errorList의 __FIELD_NAME__ 번역
					_.each(errorList, function(item, index, list) {
						item.message = item.message.replace(/__FIELD_NAME__/, fieldNameCtx[item.element.id]);
					});

					//logToJson(errorMap, "showErrors - errorMap processed");
					//logToJson(_.map(errorList, function(item) { return { message: item.message, elementId: item.element.id }; }), "showErrors - errorList processed");

					//Custom Show Errors
					//_.each(errorList, function(item) { $(item.element).addClass("error"); });
					var errorMsgs = _.values(errorMap).join("\n");
					alert(errorMsgs);
					errorList[0].element.focus();
					//return false;
				};

				return this.defaultShowErrors();
			};
			return showError;
		};


		/** json으로 Observable 값을 설정 */
		self.copyObservableValues = function(vm, jsonObject, setNullOrEmptyIfNotMatch/*true*/) {
			setNullOrEmptyIfNotMatch = (setNullOrEmptyIfNotMatch !== false);
			//copy value if viewmodel has key matched observable
			_.each(vm, function(value, key, list) {
				//ObservableArray는 제외
				if(!self.isObservableArray(value) || (value["jsonProperty"] === true)) {
					if(ko.isObservable(value) && !ko.isComputed(value)) {
						if(!_.isEmpty(jsonObject) && _.has(jsonObject, key)) {
							//값 지정...
							var oldValue = value();
							var newValue = jsonObject[key];
							if(_.isBoolean(oldValue) && _.isString(newValue)) {
								newValue = _.contains(["true", "t", "y"], newValue.toLowerCase());
							}
							value(newValue);
						} else if(setNullOrEmptyIfNotMatch) {
							if(_.isString(value())) {
								value('');
							} else {
								value(null);
							}
						}
					}
				}
			});
		};

		/** Observable의 값을 초기값으로 설정 (초기화 값 map 전달가능) */
		self.emptyObservableValues = function(vm, defaultValueMap /*null*/) {
			defaultValueMap = defaultValueMap || {};
			return self.copyObservableValues(vm, defaultValueMap, true);
		};

		/** 주어진 속성이 ObservableArray인지 검사 */
		self.isObservableArray = function(value) {
			//observable이고 destroyAll이 있음
			return ko.isObservable(value) && value.destroyAll !== undefined;
		};

		/** Observable의 값을 JSON으로 추출 */
		self.toJS = function(vm) {
			//return ko.toJS(vm);
			var json =
				_.chain(vm)
				.map(function(value, key, list) { return {key:key, value:value}; })
				.reduce(function(result, item) {
					if(item.value != null) {
                        if(item.value["jsonProperty"] === false) {
                            return result;
                        }
						if(item.value["jsonProperty"]) {
                            var jsonProperty = item.value["jsonProperty"];
                            if(_.isString(jsonProperty)) {
                                result[jsonProperty] = item.value();
                            }
							result[item.key] = item.value();
						}
						if(ko.isObservable(item.value) && !ko.isComputed(item.value) && !self.isObservableArray(item.value)) {
							result[item.key] = item.value();
						}
					}
					return result;
				}, {})
				.value();
			return json;
		};

		/*************************************************************************
		 * Field-Specific ViewModel Helper Functions
		 */

		/**
		 * Calendar와 연동된 Date Range 선택시 시작날짜와 종료일의 역전을 방지
		 * @param startDateKoObservable 시작날짜 객체 ko.observable("YYYY-MM-DD")
		 * @param endDateKoObservable 종료날짜 객체 ko.observable("YYYY-MM-DD")
		 */
		self.preventReversalOfKoDateRange = function(startDateKoObservable, endDateKoObservable, dateFormat) {
			var _dateFormat = dateFormat || "YYYY-MM-DD";
			//Date Range - Start/End 역전 제한
			startDateKoObservable.subscribe(function(newRealPayDateStart) {
				var start = moment(newRealPayDateStart, _dateFormat);
				var end = moment(endDateKoObservable(), _dateFormat);
				if(end.diff(start) < 0) {
					endDateKoObservable(newRealPayDateStart);
				}
			});
			endDateKoObservable.subscribe(function(newRealPayDateEnd) {
				var start = moment(startDateKoObservable(), _dateFormat);
				var end = moment(newRealPayDateEnd, _dateFormat);
				if(start.diff(end) > 0) {
					startDateKoObservable(newRealPayDateEnd);
				}
			});
		};
	}; //var VMUtil = function() {

	vmUtil = new VMUtil(); //export to global

}());