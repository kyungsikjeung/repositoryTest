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
			if(self["_extendValidatableVM_processed"] === true) {
				return; //중복확장 방지
			}

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

			//확장되었다는 플래그 처리
			self._extendValidatableVM_processed = true;
		}; //extendValidatableVM


		/**
		 * 기본 ValidatableVM 확장: 주어진 VM을 Validation이 가능하게 공통 처리로 확장
		 * 하위 VM에서 동작을 다르게 하려면 validationRules Override 처리 필요
		 * - validationRules
		 * - validate
		 */
		extender.extendGridVM = function(targetVM) {
			var self = targetVM;
			if(self["_extendGridVM_processed"] === true) {
				return; //중복확장 방지
			}

			// Grid column template
			self.gridColumnTemplate = self.gridColumnTemplate || {};

			/**
			 * Row Index Template
			 *   사용방법 ==> template: self.gridColumnTemplate.rowIndexTemplate
			 */
			self.gridColumnTemplate.rowIndexTemplate = function(data) {
				var page = ( self.grid.dataSource.page() - 1 );
				var pageSize = self.grid.dataSource.pageSize();
				var index = self.grid.dataSource.indexOf(data) + 1;
				return page * pageSize + index;
			};

			/**
			 * Boolean Checkbox Formatter Template. (ReadOnly)
			 *   사용방법 ==> template: self.gridColumnTemplate.nullableBooleanCheckboxFormatter('ynField', false)
			 */
			self.gridColumnTemplate.nullableBooleanCheckboxFormatter = function(field, defaultValue) {
				var closure = {
					field: field,
					defaultValue: (defaultValue!==false)
				};
				closure.field; //dummy for eclipse stupid validation
				var template = function(dataItem) {
					var value = dataItem[closure.field] || closure.defaultValue;
					return $.format("<input type='checkbox' {0} disabled='disabled'>", (value)?"checked='checked'":"");
				};
				return template;
			};

			/**
			 * Boolean Checkbox Formatter Template. (ReadOnly)
			 *   사용방법 ==> template: self.gridColumnTemplate.booleanCheckboxFormatter('ynField')
			 */
			self.gridColumnTemplate.booleanCheckboxFormatter = function(field) {
				return self.gridColumnTemplate.nullableBooleanCheckboxFormatter(field, false);
			};

			/**
			 * Row Select Checkbox Formatter Template.
			 *   사용방법 ==> template: self.gridColumnTemplate.gridRowCheckboxFormatter('checkBoxClassName')
			 *   			 template: self.gridColumnTemplate.gridRowCheckboxFormatter({checkBoxClassName: 'kendoGridRowCheckbox', isCheckAllAtTitle: false, field: 'field', fnIsEnabled: function })
			 */
			self.gridColumnTemplate.gridRowCheckboxFormatter = function(options/*기본값: kendoGridRowCheckbox*/) {
				var defaultOptions = {
					checkBoxClassName: 'kendoGridRowCheckbox',
					isCheckAllAtTitle: false,
					field: 'gridRowChecked'
				};
				if(!_.isObject(options)) {
					options = { checkBoxClassName: options || 'kendoGridRowCheckbox' };
				}
				options = _.defaults(options, defaultOptions);

				if(options.isCheckAllAtTitle === true) {
					//header용 checkAll. kendo template 반환
					return $.format("<input type='checkbox' class='{0}' data-role='gridRowCheckboxAll' data-field='{1}'/>", options.checkBoxClassName, options.field);
				} else {
					//dataCell용 checkbox. kendo template 반환
					if(_.isFunction(options['fnIsEnabled'])) {
						//체크가능여부 판단 함수 전달된 경우
						var closure = {
							field: options.field,
							checkBoxClassName: options.checkBoxClassName,
							fnIsEnabled: options.fnIsEnabled
						}
						var template = function(dataItem) {
							var disabledTag = (closure.fnIsEnabled(dataItem) !== false)?'':' disabled';
							return $.format("<input type='checkbox' class='{0}' data-role='gridRowCheckbox' data-field='{1}' {2}/>", closure.checkBoxClassName, closure.field, disabledTag);
						};
						return template;
					}
					return $.format("<input type='checkbox' class='{0}' data-role='gridRowCheckbox' data-field='{1}'/>", options.checkBoxClassName, options.field);
				}
			};

			/**
			 * Moment Formatter Template. (기본형식: 'YYYY-MM-DD HH:mm:ss')
			 *   사용방법 ==> template: self.gridColumnTemplate.momentFormatter('regDate')
			 *              template: self.gridColumnTemplate.momentFormatter('regDate', 'YYYYMMDDhhmmss')
			 */
			self.gridColumnTemplate.momentFormatter = function(field, momentFormatString, momentParseFormatString) {
				var closure = {
					field: field,
					momentFormatString: momentFormatString || 'YYYY-MM-DD HH:mm:ss',
					momentParseFormatString: momentParseFormatString || momentFormatString
				};
				closure.field; //dummy for eclipse stupid validation
				closure.momentFormatString; //dummy for eclipse stupid validation
				closure.momentParseFormatString; //dummy for eclipse stupid validation
				var template = function(dataItem) {
					var value = dataItem[closure.field] || "";
					if(_.string.isBlank(value)) {
						return value;
					}
					return moment(dataItem[closure.field], closure.momentParseFormatString).format(closure.momentFormatString);
				};
				return template;
			};

			/**
			 * Moment Formatter Template. (기본형식: 'YYYY-MM-DD')
			 *   사용방법 ==> template: self.gridColumnTemplate.momentISOFormatter('regDate')
			 */
			self.gridColumnTemplate.momentISOFormatter = function(field) {
				return self.gridColumnTemplate.momentFormatter(field, 'YYYY-MM-DD');
			};

			/**
			 * 코드맵으로 변환하여 출력. 코드맵에 없으면 원본값 출력, 또는  defaultCode값이 있다면 기본값으로 출력
			 *   사용방법 ==> template: self.gridColumnTemplate.codeMapFormatter('heroType', {'A':'슈퍼맨', ...})
			 */
			self.gridColumnTemplate.codeMapFormatter = function(field, codeMap, defaultCode) {
				var closure = {
					field: field,
					codeMap: codeMap,
					defaultCode : defaultCode
				};
				var template = function(dataItem) {
					var value = _.isNull(dataItem[closure.field])? "":dataItem[closure.field] ;

					if(_.has(closure.codeMap, value)) {
						return closure.codeMap[value];
					}
					if( !_.isEmpty(defaultCode)) {
						return _.isEmpty(value) ? defaultCode : value; //없으면 기본코드값 반환
					} else {
						return value; //없으면 코드값 원본 반환
					}

				};
				return template;
			};

			/**
			 * [자식객체용] Moment Formatter Template. (기본형식: 'YYYY-MM-DD')
			 *   사용방법 ==> template: self.gridColumnTemplate.momentFormatterForChild('regDate')
			 */
			self.gridColumnTemplate.momentFormatterForChild = function(child, field, momentFormatString, momentParseFormatString) {
				var closure = {
					child: child,
					field: field,
					momentFormatString: momentFormatString || 'YYYY-MM-DD HH:mm:ss',
					momentParseFormatString: momentParseFormatString || momentFormatString
				};
				closure.field; //dummy for eclipse stupid validation
				closure.momentFormatString; //dummy for eclipse stupid validation
				closure.momentParseFormatString; //dummy for eclipse stupid validation
				var template = function(dataItem) {
					var child = dataItem[closure.child];
					var value = "";
					if( child ) {
						value = child[closure.field];
						if(!_.isEmpty(value)) {
							value = moment(dataItem[closure.field], closure.momentParseFormatString).format(closure.momentFormatString);
						}
					}
					return value;
				};
				return template;
			};

			/**
			 * [자식객체용] 코드맵으로 변환하여 출력. 코드맵에 없으면 원본값 출력
			 *   사용방법 ==> template: self.gridColumnTemplate.codeMapFormatter('heroType', {'A':'슈퍼맨', ...})
			 */
			self.gridColumnTemplate.codeMapFormatterForChild = function(child, field, codeMap) {
				var closure = {
					child: child,
					field: field,
					codeMap: codeMap
				};
				var template = function(dataItem) {
					var child = dataItem[closure.child];
					var value = "";
					if( child ) {
						value = child[closure.field] || "";
						if(_.has(closure.codeMap, value)) {
							return closure.codeMap[value];
						}
					}
					return value; //없으면 코드값 원본 반환
				};
				return template;
			};


			/**
			 * Number Formatter Template. (ReadOnly)
			 *   사용방법 ==> template: self.gridColumnTemplate.numberFormatter('numberField')
			 */
			self.gridColumnTemplate.numberFormatter = function(field) {
				var closure = {
					field: field
				};
				closure.field; //dummy for eclipse stupid validation
				var template = function(dataItem) {
					var value = dataItem[closure.field] || 0;
					return _.string.numberFormat(value);
				};
				return template;
			};

			/**
			 *   사용방법 ==> template: self.gridColumnTemplate.periodDateFormatter('startDate', 'endDate')
			 *   시작일 ~ 종료일 표시
			 *   예)2018-01-01 ~ 2018-01-02
			 */
			self.gridColumnTemplate.periodDateFormatter = function(field1, field2, momentFormatString, momentParseFormatString) {
				var closure = {
						field1: field1,
						field2: field2,
						momentFormatString: momentFormatString || 'YYYY-MM-DD',
						momentParseFormatString: momentParseFormatString || momentFormatString
					};
					closure.field1; //dummy for eclipse stupid validation
					closure.field2; //dummy for eclipse stupid validation
					closure.momentFormatString; //dummy for eclipse stupid validation
					closure.momentParseFormatString; //dummy for eclipse stupid validation
					var template = function(dataItem) {
						var value1 = dataItem[closure.field1] || "";
						if(_.string.isBlank(value1)) {
							return '';
						}
						return moment(dataItem[closure.field1], closure.momentParseFormatString).format(closure.momentFormatString)+"~"+moment(dataItem[closure.field2], closure.momentParseFormatString).format(closure.momentFormatString);
					};
					return template;
			};



			//확장되었다는 플래그 처리
			self._extendGridVM_processed = true;
		}; //extendGridVM


		/**
		 * 기본 PopupVMVM 확장: 주어진 VM을 레이어팝업이 가능하게 공통 처리로 확장
		 * 하위 VM에서 동작을 다르게 하려면 popupOptions Override 처리 필요
		 * 하위 VM에서 onSubmitClick Override 처리하여 확인버튼의 액션 처리 필요
		 * - popupOptions
		 * - onSubmitClick
		 * - open
		 * - close
		 */
		extender.extendPopupVM = function(targetVM) {
			var self = targetVM;
			if(self["_extendPopupVM_processed"] === true) {
				return; //중복확장 방지
			}
			if(_.isBlank(self["popupId"])) {
				console.log("VM에 popupId를 정의해주세요.");
			}
			if(_.isBlank(self["popupOptions"])) {
				console.log("VM에 popupOptions를 정의해주세요.");
			}

			//Super
			extender.extendBaseVM(self);

			self.popupElement = $(self.popupId);

			self.open = function() {
				self.popupElement.dialog('open');
			};
			self.close = function() {
				self.popupElement.dialog('close');
			};

			/**
			 *  Popup Options 설정방법
			 *
			 *  기본값: autoOpen=false, modal=true, width: auto, height: auto
			 *
			 *	버튼 커스터마이징 방법
			 *  하단 버튼 모두 삭제는 buttons: [] 로 처리
			 *  하단 버튼 커스터마이징은 self.popupOptions에 buttons추가하여 수정
			 	예제)
			 	buttons: [
					{
						text: '커스텀버튼1',
						icons: { primary: 'icon-checkmark5' }, //원하는 아이콘 설정 또는 삭제
						click: callbackFunction1
					},
					{
						text: '커스텀버튼2',
						icons: { primary: 'icon-cross3' }, //원하는 아이콘 설정 또는 삭제
						click: callbackFunction2
					}
				]
			};
			*/
			self.defaultPopupOptions = {
				autoOpen: false,
				modal: true,
				buttons: [
					{
						text: '확인',
						icons: { primary: 'icon-checkmark5' },
						click: function() { self.onSubmitClick(); }
					},
					{
						text: '취소',
						icons: { primary: 'icon-cross3' },
						click: self.close
					}
				]
			};
			self.popupOptions = _.defaults( self.popupOptions, self.defaultPopupOptions);
			self.popupElement.dialog(self.popupOptions);

			//확장되었다는 플래그 처리
			self._extendPopupVM_processed = true;
		}; //extendPopupVM

		/**
		 * 기본 PopupVMVM 확장: 주어진 VM을 Iframe팝업이 가능하게 공통 처리로 확장
		 * 하위 VM에서 동작을 다르게 하려면 popupOptions Override 처리 필요
		 * 하위 VM에서 onSubmitClick Override 처리하여 확인버튼의 액션 처리 필요
		 * - popupOptions
		 * - onSubmitClick
		 * - open
		 * - close
		 */
		extender.extendIframePopupVM = function(targetVM) {
			if( ezUtil.ezInitialized === false ){
				//ezUtil Initialize가 진행되지 않은 경우 Initialize 실행
				ezUtil.ezInitialize();
			}
			var self = targetVM;
			if(self["_extendIframePopupVM_processed"] === true) {
				return; //중복확장 방지
			}
			if(_.isBlank(self["popupId"])) {
				console.log("VM에 popupId를 정의해주세요.");
			}
			if(_.isBlank(self["popupOptions"])) {
				console.log("VM에 popupOptions를 정의해주세요.");
			}
			if(_.isBlank(self["iframeSrc"])) {
				console.log("VM에 iframeSrc를 정의해주세요.");
			}

			//Super
			extender.extendBaseVM(self);

			self.popupElement = $(self.popupId);

			self.open = function() {
				self.popupElement.dialog('open');
			};
			self.close = function() {
				self.popupElement.dialog('close');
			};

			//Submit Callback
			self.onSubmitClick = function() {
				console.log("submit");
			};


			/**
			 *  Popup Options 설정방법
			 *
			 *  기본값: autoOpen=false, modal=true, width: auto, height: auto
			 *
			 *	버튼 커스터마이징 방법
			 *  하단 버튼 모두 삭제는 buttons: [] 로 처리
			 *  하단 버튼 커스터마이징은 self.popupOptions에 buttons추가하여 수정
			 	예제)
			 	buttons: [
					{
						text: '커스텀버튼1',
						icons: { primary: 'icon-checkmark5' }, //원하는 아이콘 설정 또는 삭제
						click: callbackFunction1
					},
					{
						text: '커스텀버튼2',
						icons: { primary: 'icon-cross3' }, //원하는 아이콘 설정 또는 삭제
						click: callbackFunction2
					}
				]
			};
			*/
			self.defaultPopupOptions = {
				autoOpen: false,
				modal: true,
				minWidth: 1000,
				width: 1000,
				minHeight: 630,
				height: 630,
				buttons: [
					{
						text: '확인',
						icons: { primary: 'icon-checkmark5' },
						click: function() { self.onSubmitClick(); }
					},
					{
						text: '닫기',
						icons: { primary: 'icon-cross3' },
						click: self.close
					}
				],
				resize : function(event, ui){
				},
				resizeStart : function(event, ui){
				},
				resizeStop : function(event, ui){
					$(this).find('iframe').width((ui.size.width-44));
					$(this).find('iframe').height((ui.size.height-135));
				}, close: function (event, ui){
					self.popupElement.dialog('close');

				}
			};
			self.popupOptions = $.extend( {}, self.defaultPopupOptions, self.popupOptions);
			var iframeId = _.startsWith(self["popupId"], "#") ? self["popupId"].substr(1) : self["popupId"];
			//form으로 Iframe에 post전송 시 페이지이동을 방지하기 위해 form에 target을 지정하는데, iframe name으로 지정해야함
			var iframeName = _.startsWith(self["popupId"], "#") ? self["popupId"].substr(1) : self["popupId"];
			iframeName = iframeName + "-name";
			var iframeWidth = self.popupOptions.width-44;
			var iframeHeighth = self.popupOptions.height-140;
			var iframeSrcAttr = _.isEmpty(self.iframeSrc) ? '' : ' src="'+self.iframeSrc+'"';
			var iframe = $('<iframe id="'+ iframeId +'" name="' + iframeName + '"'+ iframeSrcAttr +' width='+iframeWidth+' height='+iframeHeighth+' allowfullscreen></iframe>');
			self.popupElement.append(iframe).appendTo("body").dialog(self.popupOptions);

			//확장되었다는 플래그 처리
			self._extendIframePopupVM_processed = true;
		}; //extendPopupVM

	}; //var VMExtender = function() {

	vmExtender = new VMExtender(); //export to global

}());