/**
 * Ablecoms ezutil js library
 *
 * Helper 및 공통 초기화 담당
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
(function() {

	/** console unsupported browser fallback */
	if(_.isUndefined(window['console'])) {
		console = {
			error: function() {},
			warn: function() {},
			info: function() {},
			log: function() {},
			debug: function() {},
			assert: function() {}
		};
	}

	/** create global message function */
	if(_.isUndefined(window['ablemessage'])) {
		messagemap = window["messagemap"] || {};
		ablemessage = function(code) {
			var lowerCode = (code || "").toLowerCase();
			if(_.has(messagemap, lowerCode)) {
				return messagemap[lowerCode];
			}
			return code;
		};
	}

	/*****************************************************************************
	 * ezUtil - 일반 Helpher Utility
	 *****************************************************************************/
	var EzUtil = function () {
		var self = this;
		self.clazz = "EzUtil";

		var _default_msg = {
			comm_loadindicator: "처리중입니다.<br/>잠시만 기다려 주세요...",
			comm_ajaxerror: "서버와의 통신도중 오류가 발생했습니다.\n잠시후 다시 시도해주세요.\n오류가 지속되면 관리자에게 문의 부탁드립니다.",
			comm_ajaxerrordetail: "[오류정보]\n -주소: %s\n -응답코드: %s (%s)",
			comm_loginrequired: "로그인이 필요합니다.",
			comm_dberror: "데이터베이스 오류.\n오류가 지속되면 관리자에게 문의 부탁드립니다."
		};

		var _msg;
		try{
			_msg = msg;
			if(_.size(_msg) == 0) {
				_msg = _default_msg;
			}
		} catch (e) {
			_msg = _default_msg;
		}

		//메시지 지역화: msg배열에서 메시지코드 치환
		self.lang = function(code) {
			if(_.has(_msg, code)) {
				return _msg[code];
			} else {
				return 'unknown message: ' + code;
			}
		};

		var _MESSAGES = {
				//"처리중입니다. 잠시만 기다려 주세요...",
				loadingIndicatorMessage : self.lang("comm_loadindicator").replace(/\\n/gi, "\n"),
				//"서버와의 통신도중 오류가 발생했습니다.\n잠시후 다시 시도해주세요.\n오류가 지속되면 관리자에게 문의 부탁드립니다.",
				ajaxErrorMessage: self.lang("comm_ajaxerror").replace(/\\n/gi, "\n"),
				//"[오류정보]\n -주소: %s\n -응답코드: %s (%s)"
				ajaxErrorDetailMessage: self.lang("comm_ajaxerrordetail").replace(/\\n/gi, "\n")
			};

		/*************************************************************************
		 * 전역 초기화
		 */

		/** 기본 초기화 작업 수행. 모든페이지에서 사용 */
		self.ezInitialize = function(blockUsingPageAjaxIndicator) {
			//Underscore String init
			self.ezInitializeUnderscoreString();

			//jQuery validate init
			self.ezInitializeJQueryValidate();

			//jQuery numeric init
			self.ezInitializeNumericInput();

			//turn off Autofill
			self.suppressAutofill();

			//Ajax 전역 설정
			self.ezInitializeAjaxHelpher();

			//Ajax 전역 Indicator
			if(blockUsingPageAjaxIndicator !== true) {
				self.ezRegisterPageAjaxIndicator();
			}
		};

		/** Unserscore.string 초기화 */
		self.ezInitializeUnderscoreString = function() {
			if(_.string === undefined && s === undefined) {
				console.log('Underscore.string are not included. ezUtil skip ezInitializeUnderscoreString()');
				return false;
			}
			//Unserscore String을 Unserscore에 mixin
			if(!_.isUndefined(_.str)) {
				_.mixin(_.str.exports()); //Unserscore String Old Version
			} else {
				_.string = s;
				_.mixin(s.exports()); //Unserscore String Newer Version
			}
		};

		/** jQuery validate 초기화 */
		self.ezInitializeJQueryValidate = function() {
			if($.validator === undefined) {
				console.log('Underscore.string are not included. ezUtil skip ezInitializeUnderscoreString()');
				return false;
			}

			/** Global Setting */
			$.validator.setDefaults({
				ignore: [],
				onsubmit: false, onfocusout: false, onkeyup: false, onclick: false, focusInvalid: true, focusCleanup: false,
				//errorClass: "error", validClass: "valid", errorElement: "em", wrapper: "li", errorElement: "label"
				showErrors: vmUtil.createShowErrorFunctionWithLabelMap(),
				errorPlacement: function(error, element) {
					//error.insertAfter(element);
					//alert(error.html());
				}
			});
		};

		/** jQuery Numeric 연관 Input 초기화 */
		self.ezInitializeNumericInput = function() {
			if($.fn.numeric === undefined) {
				console.log('jquery.numeric are not included. ezUtil skip ezInitializeNumericInput()');
				return false;
			}
			$(".numeric").numeric();
			$(".integer").numeric(false, function() { this.value = ""; this.focus(); });
			$(".positive-numeric").numeric({ negative: false }, function() { this.value = ""; this.focus(); });
			$(".positive-integer").numeric({ decimal: false, negative: false }, function() { this.value = ""; this.focus(); });
		};

		/** 페이지 자동 Ajax Indicator 등록 */
		self.ezRegisterPageAjaxIndicator = function() {
			if($.blockUI === undefined) {
				console.log('jquery.blockUI are not included. ezUtil skip ezRegisterPageAjaxIndicator()');
				return false;
			}
			//jQuery Event 발생 순서 - http://api.jquery.com/Ajax_Events/
			//ajaxStart()
			//ajaxSend(event, jqXHR, ajaxOptions)
			//ajaxSuccess(event, XMLHttpRequest, ajaxOptions) 또는 .ajaxError(event, jqXHR, ajaxSettings, thrownError)
			//ajaxComplete(event, XMLHttpRequest, ajaxOptions)
			//ajaxStop()
			$(document).ajaxStart(function() { self.block(); });
			$(document).ajaxError(function() { self.unblock(); });
			$(document).ajaxSuccess(function() { self.unblock(); });
			$(document).ajaxComplete(function(event, XMLHttpRequest, ajaxOptions) { self.unblock(); /*self.initLoginDurationTimer();*/ });
			$(document).ajaxStop(function() { self.unblock(); });
		};

		/** Ajax관련 전역 초기화 */
		self.ezInitializeAjaxHelpher = function() {
			$.ajaxSetup({
				type: "POST",
				dataType: "json",
				cache: false,
				//contentType: "application/x-www-form-urlencoded",
				contentType: "application/json",
				processData: false, //true이면 application/x-www-form-urlencoded을 위해 data를 key=value로 인코딩
				beforeSend: function(jqXHR,options) {
			        if (options.contentType == "application/json" && typeof options.data != "string") {
			            options.data = JSON.stringify(options.data);
			        }
			    }
				// Data converters: Keys separate source (or catchall "*") and destination types with a single space
				/* converters: {
					// Convert anything to text
					"* text": String,
					// Text to html (true = no transformation)
					"text html": true,
					// Evaluate text as a json expression
					"text json": function(data) {
						data = data.replace(/^(\ufeff)+/gi, ""); //Remove BOM at Response
						return jQuery.parseJSON(data);
					},
					// Parse text as xml
					"text xml": jQuery.parseXML
				} */
			});

			$(document).ajaxError(self._onAjaxErrorGlobalHandler);
		};

		/** onAjaxError 전역 핸들러 */
		self._onAjaxErrorGlobalHandler = function(event, jqXHR, ajaxSettings, thrownError) {
			//If either of these are true, then it's not a true error and we don't care
		    if (jqXHR.status === 0 || jqXHR.readyState === 0) {
		    	console.log("_onAjaxErrorGlobalHandler ==> ignored - jqXHR.status === 0 || jqXHR.readyState === 0");
		        return;
		    }

		    console.log("_onAjaxErrorGlobalHandler ==> ", event, jqXHR, ajaxSettings, thrownError);
			var defaultMessage = _MESSAGES.ajaxErrorMessage;
			try {
				var url = ajaxSettings.url;
				var errorMessage = _.sprintf(_MESSAGES.ajaxErrorDetailMessage, url, jqXHR.status, jqXHR.statusText);
				alert(defaultMessage + "\n\n" + errorMessage);
			} catch (e) {
				alert(defaultMessage);
				console.log("_onAjaxErrorGlobalHandler ERROR ==> ", e);
			};
		};


		/** 자동 완성 방지 */
		self.suppressAutofill = function() {
			//chrome autocomplete 방지
			_.defer(function() {
				setTimeout(function(){
					$("input[data-id]").each(function(idx, element) {
						$(this).attr("id", $(this).attr("data-id"));
					});
					$("input[data-name]").each(function(idx, element) {
						$(this).attr("name", $(this).attr("data-name"));
					});
					$("input[name]:not([id])").each(function(idx, element) {
						$(this).attr("id", $(this).attr("name"));
					});
				}, 10);
			});
		};

		/*************************************************************************
		 * Loading Indicator Helpher - jQuery BlockUI Plugin 사용
		 *
		 * [사용방법]
		 *
		 * 		ezUtil.block(): 페이지 Block
		 * 	 	ezUtil.unblock(): 페이지 UnBlock
		 * 		ezUtil.block("#id"): 특정 Element Block
		 * 		ezUtil.unblock("#id"): 특정 Element Block
		 * 		ezUtil.unblockAll(): Block 했던 모든것 UnBlock
		 */
		/** Indicator용 메지지 포맷팅 */
		self.formatIndicatorMessage = function(message) {
			//message = message || _MESSAGES.loadingIndicatorMessage; //default Message 처리
			//var src = "/images/blockui/busy4.gif";
			//return _.sprintf("<div style='color:#555555;font-size:12px;font-weight:bold;'><br/><img src='%s' style='vertical-align: middle;'/> %s<br/><br/></div>", src, message);

			//고객사에서 박스없이 이미지만 표시 요청
			var src = (contextPath||"") + "/web/lib/blockui/busy5.gif";
			return _.sprintf("<div><img src='%s' style='width:48px; height:48px'/><div>", src);
		};

		/** true이면 화면의 Block을 UnBlock할수 없게 함 */
		self.isLockBlock = false;
		self.lockBlock = function() {
			self.isLockBlock=true;
		};
		self.releaseBlock = function() {
			self.isLockBlock=false;
		};

		/** 특정 Element 또는 Page전체를 Block */
		self.block = function(jQuerySelector, message) {
			var config = {
				message : self.formatIndicatorMessage(message),
				css: {
	                width: '100%', left: 0, border: 'none', padding: '5px',
	                backgroundColor: 'transparent', opacity: .4
	            }
			};
			if (_.isEmpty(jQuerySelector)) {
				$.blockUI(config);
			} else {
				$(jQuerySelector).block(config);
			}
			self._blockedList.push(jQuerySelector);
		};

		/** 특정 Element 또는 Page전체를 UnBlock */
		self.unblock = function(jQuerySelector) {
			if(self.isLockBlock) return;
			if (_.isEmpty(jQuerySelector)) {
				$.unblockUI();
			} else {
				$(jQuerySelector).unblock();
			}
			self._blockedList = _.filter(self._blockedList, function(item){ return item != jQuerySelector; });
		};

		/** 내부적인 blockList관리 - unblockAll로 모두 해제시 사용 */
		self._blockedList = [];

		/** Block 했던것을 모두 Unblock */
		self.unblockAll = function() {
			if(!_.isEmpty(self._blockedList)){
				_.each(self._blockedList, self.unblock);
				self.unblock(null);
				self._blockedList = [];
			}
		};

		/*************************************************************************
		 * Common Util
		 */

		/**
		 * 객체배열의 특정 프로퍼티를 키로 맵을 구성
		 * listToMap( [{key:"a1", b:1}, {key:"a2", b:2}], "a" ) => { a1: {key:"a1", b:1}, a2: {key:"a2", b:2} }
		 */
		self.listToMap = function(list, keyField) {
			return _.reduce(list, function(result, item) { result[item[keyField]] = item; return result; }, {});
		};

		/** 첨부 이미지를 팝업 */
		self.popUpAttachedImage = function(url, target/*default: _blank*/) {
			window.open(url, target || "_blank", "width=400, height=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no" );
		};
		/** 첨부 파일을 팝업 */
		self.popUpAttachedFile = function(url, target/*default: _blank*/) {
			window.open(url, target || "_blank", "width=400, height=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no" );
		};
		/** 첨부 파일은 다운로드 (GET) */
		self.downloadAttachedFile = function(url, queryParamObject) {
			queryParamObject = _.defaults(queryParamObject||{}, { forceDownload: true });
			var urlWithParam = $.param.querystring(url, queryParamObject);
			$.fileDownload(urlWithParam);
		};
		/** excel파일 다운로드 */
		self.downloadExcelFile = function(url, data) {
			var options = {
				httpMethod: "POST",
				data: data
			};
			$.fileDownload(url, options);
		};

		/** 현재의 QueryString을 Json으로 획득 */
		self.deparamQueryString = function() {
			return _.omit($.deparam.querystring(), ["lang"]);
		};


		/*************************************************************************
		 * Communication Util
		 */

		/**
		 *  message의 formmat처리
		 *   __FIELD_NAME__: 필드명으로 변환. fieldNameMap이용, 메시지코드로 변환시도
		 */
		self.formatFieldErrorMessage = function(message, field, fieldNameMap/* { field : "fieldName Or msgCodeID", ... } */, fieldMsgCodePrefix) {
			var fieldName = self.resolveFieldName(field, fieldNameMap, fieldMsgCodePrefix);
			return message.replace("__FIELD_NAME__", fieldName);
		};
		/** 필드명 획득. 메시지 변환처리 */
		self.resolveFieldName = function(field, fieldNameMap/* { field : "fieldName Or msgCodeID", ... } */, fieldMsgCodePrefix) {
			fieldMsgCodePrefix = fieldMsgCodePrefix || "com.col."; //convention
			field = field || "";

			var fieldName = field;

			//1. fieldNameMapper를 이용
			fieldNameMap = fieldNameMap || {};
			if(!_.isEmpty(fieldNameMap) && _.has(fieldNameMap, field)) {
				fieldName = fieldNameMap[fieldName];
				return ablemessage(fieldName);
			}

			//2. field를 이용
			if (!_.isEmpty(fieldMsgCodePrefix)) {
				fieldName = fieldMsgCodePrefix + fieldName;
			}
			return ablemessage(fieldName);
		};

		/** REST API 공통응답 처리 - return: response.isSuccess 성공인지 실패인지 반환 */
		self.checkRESTResponseMsg = function(response,
				fieldCtrlMap /* { field : "inputCtrlName", ... } */,
				fieldNameMap /* { field : "fieldName Or msgCodeID", ... } */) {
			console.log("checkRESTResponseMsg ==> ", response);
			if(_.isString(response)) {
				response = JSON.parse(response);
			}

			//{
			//	  "status" : "ERROR",
			//	  "msg" : null,
			//	  "cbCmd" : null,
			//	  "cbParam" : null,
			//	  "body" : null,
			//	  "error" : [ {
			//	    "objectName" : "mstCodeDT",
			//	    "field" : "isDelete",
			//	    "rejectedValue" : null,
			//	    "code" : "NotNull",
			//	    "message" : "__FIELD_NAME__ 항목은 필수입니다."
			//	  }, ...]
			//}

			if(_.isUndefined(response.status)) {
				return false;
			}

			// 1. 공통 메시지 처리 -> falldown
			if (!_.isEmpty(response.msg)) {
				alert(response.msg);
			}

			// 2. 공통 콜백 명령 처리 -> falldown
			if (!_.isEmpty(response.cbCmd)) {
				switch (response.cbCmd) {
				case 'loginrequired':
					//로그인 시도 검토
					break;
				case 'redirect':
					document.location.href = response.cbParam;
					break;
				default:
					break;
				}
			}

			// 3. Validation Error 메시지 처리
			if (!_.isEmpty(response.fieldError)) {
				//show multiple message
				var errorMessage =
					_.chain(response.fieldError)
					.map(function(error) {
						return self.formatFieldErrorMessage(error.message, error.field, fieldNameMap);
					})
					.value().join("\r\n");
				if(!_.isEmpty(errorMessage)) {
					alert(errorMessage);
					response.message = null;
				}
				//focus 처리
				var fieldToFocus = _.chain(response.fieldError).map(function(error) { return error.field; }).first().value();
				if(!_.isEmpty(fieldToFocus)) {
					fieldCtrlMap = fieldCtrlMap || {};
					if(!_.isEmpty(fieldCtrlMap) && _.has(fieldCtrlMap, fieldToFocus)) {
						fieldToFocus = fieldCtrlMap[fieldToFocus];
					}
					var targetCtrl = $("#"+fieldToFocus);
					targetCtrl.focus().select();
				}
			}

			return response.status == "SUCCESS";
		};

		//Admin 페이지 GNB,LNB 현재 페이지 활성화
		self.setAdminGnbLnbActivate = function(gnbIndexOrSelector,lnbIndexOrSelector) {
			if( _.isNumber(gnbIndexOrSelector)) {
				$(".gnb_box .gnb li:eq(" + gnbIndexOrSelector + ")").addClass("on");
			} else if(_.isString(gnbIndexOrSelector)) {
				$(".gnb_box .gnb").find(gnbIndexOrSelector).addClass("on");
			}

			if( _.isNumber(lnbIndexOrSelector)) {
				$("#lnb ul li:eq("+ lnbIndexOrSelector +")").addClass("on");
			} else if( _.isString(lnbIndexOrSelector)) {
				$("#lnb ul").find(lnbIndexOrSelector).addClass("on");
			}
		};
	};

	ezUtil = new EzUtil();

}());

