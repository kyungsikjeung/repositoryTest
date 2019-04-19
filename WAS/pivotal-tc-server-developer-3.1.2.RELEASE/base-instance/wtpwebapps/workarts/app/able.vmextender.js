/**
 * Ablecoms VMExtender
 *
 * ViewModel Extend Helper
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
(function() {

	/*****************************************************************************
	 * VMExtender - ViewModel Extender Helpher
	 * VMUtil의 함수들을 VM자체에 확장
	 *****************************************************************************/
	var VMExtender = function() {
		var extender = this;
		extender.clazz = "VMExtender";

		/**
		 * 기본 BaseVM 확장: 주어진 VM에 편리하게 Util Function을 직접 확장
		 * - createShowErrorFunctionWithLabelMap
		 * - copyObservableValues
		 * - emptyObservableValues
		 * - isObservableArray
		 * - toJS
		 * - preventReversalOfKoDateRange
		 * - ajax
		 **/
		extender.extendBaseVM = function(targetVM) {
			var self = targetVM;
			if(self["_extendBaseVM_processed"] === true) {
				return; //중복확장 방지
			}

			if(_.isBlank(self["clazz"])) {
				throw "VM에 clazz를 정의해주세요.";
			}

			//VMUtil Extend
			self.createShowErrorFunctionWithLabelMap = function(labelMap) {
				return vmUtil.createShowErrorFunctionWithLabelMap;
			};
			self.copyObservableValues = function(jsonObject, setNullOrEmptyIfNotMatch/*true*/) {
				return vmUtil.copyObservableValues(self, jsonObject, setNullOrEmptyIfNotMatch);
			};
			self.emptyObservableValues = function(defaultValue/*null*/) {
				return vmUtil.emptyObservableValues(self, defaultValue);
			};
			self.isObservableArray = function(value) {
				return vmUtil.isObservableArray(value);
			};
			self.toJS = function() {
				return vmUtil.toJS(self);
			};
			self.preventReversalOfKoDateRange = function(startDateKoObservable, endDateKoObservable, dateFormat) {
				return vmUtil.preventReversalOfKoDateRange(startDateKoObservable, endDateKoObservable, dateFormat);
			};

			//Ajax 요청 전송 및 공통 처리
			self.ajax = function(ajaxOption, onSuccessCallback, onErrorCallback) {
				if(_.isEmpty(ajaxOption.url)) {
					console.error("ajax url option required.");
					return false;
				}
				//기본 success handler 처리
				_.defaults(ajaxOption, {
					success: function(data, textStatus, jqXHR) {
						if(ezUtil.checkRESTResponseMsg(data)) {
							if(_.isFunction(onSuccessCallback)) {
								onSuccessCallback(data, self);
							}
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.error("Error on ajax request. textStatus: "+textStatus+", errorThrown: " + errorThrown);
						if(_.isFunction(onErrorCallback)) {
							onErrorCallback(self);
						}
					}
				});
				//files 업로드이면 jQuery iframe transport 사용
				if(_.has(ajaxOption, "files")) {
					_.defaults(ajaxOption, {
						//contentType: 'multipart/form-data', //불필요
						iframe: true,
						processData: false
					});
				}
				//Request 전송
				console.log("ajaxOption==>", ajaxOption);
				$.ajax(ajaxOption);
			};

			//확장되었다는 플래그 처리
			self._extendBaseVM_processed = true;
		}; //extendBaseVM

		/**
		 * 기본 ValidatableVM 확장: 주어진 VM을 Validation이 가능하게 공통 처리로 확장
		 * 하위 VM에서 동작을 다르게 하려면 validationRules Override 처리 필요
		 * - validationRules
		 * - validate
		 */
		extender.extendValidatableVM = function(targetVM) {
			var self = targetVM;

			if(_.isBlank(self["formId"])) {
				console.log("VM에 formId를 정의해주세요.");
			}

			//Super
			extender.extendBaseVM(self);

			//Validation 규칙
			self.validationRules = {};

			//Validate
			self.validate = function(rules, formId) {
				rules = rules || self.validationRules;
				formId = formId || self.formId;
				var form = $(formId);
				form.removeData("validator"); //for dynamically rules setting.
				var validator = form.validate({ rules: rules });
				return form.valid();
			};
		};

	}; //var VMExtender = function() {

	vmExtender = new VMExtender(); //export to global

}());