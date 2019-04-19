/**
 * ablecoms jquery validation library extensions
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
$(function() {

	/** Global Setting */
	/*
	$.validator.setDefaults({
		ignore: [],
		onsubmit: false, onfocusout: false, onkeyup: false, onclick: false,
		focusInvalid: true, focusCleanup: false,
		//errorClass: "error", validClass: "valid", errorElement: "em", wrapper: "li", errorElement: "label"
		showErrors: vmUtil.createShowErrorFunctionWithLabelMap(),
		errorPlacement: function(error, element) {
			//error.insertAfter(element);
			//alert(error.html());
		}
	});
	*/

	// $.format 로직 개선
	// $.format("{0}", undefined), $.format("{0}", null)시 오류발생 방지
	$.validator.format = function(source, params) {
		if (arguments.length === 1) {
			return function() {
				var args = $.makeArray(arguments);
				args.unshift(source);
				return $.validator.format.apply(this, args);
			};
		}
		if (arguments.length > 2 && params.constructor !== Array) {
			params = $.makeArray(arguments).slice(1);
		}
		//deepfree fix start
		// $.format("{0}", undefined), $.format("{0}", null)의 경우에 오류 방지
		if(_.isNull(params) || _.isUndefined(params)) {
			params = [ "" ];
		}
		//deepfree fix end
		if (params.constructor !== Array) {
			params = [ params ];
		}
		$.each(params, function(i, n) {
			source = source.replace(new RegExp("\\{" + i + "\\}", "g"),
					function() {
						return n;
					});
		});
		return source;
	};
	$.format = $.validator.format;

	/** Class Rules : JS에서 Rule을 직접제어 */
	//$.validator.addClassRules({ mst_code: { maxlength: 10 } });

	/** Translated default messages for the jQuery validation plugin. */
	(function ($) {
		//validator의 기본메시지 변경
		/*
		$.extend($.validator.messages, {
			//jquery validate
			required: ablemessage("com.validate.required"),
			requiredforce: ablemessage("com.validate.required"),
			remote: ablemessage("com.validate.remote"),
			email: ablemessage("com.validate.email"),
			url: ablemessage("com.validate.url"),
			date: ablemessage("com.validate.date"),
			dateISO: ablemessage("com.validate.dateISO"),
			number: ablemessage("com.validate.number"),
			float: ablemessage("com.validate.float"),
			digits: ablemessage("com.validate.digits"),
			creditcard: ablemessage("com.validate.creditcard"),
			equalTo: ablemessage("com.validate.equalTo"),
			accept: ablemessage("com.validate.accept"),
			maxlength: $.format(ablemessage("com.validate.maxlength")),
			minlength: $.format(ablemessage("com.validate.minlength")),
			rangelength: $.format(ablemessage("com.validate.rangelength")),
			range: $.format(ablemessage("com.validate.range")),
			max: $.format(ablemessage("com.validate.max")),
			min: $.format(ablemessage("com.validate.min")),
			//jquery validate additional
			alphanumeric: ablemessage("com.validate.alphanumeric"),
			lettersonly: ablemessage("com.validate.lettersonly"),
			nowhitespace: ablemessage("com.validate.nowhitespace"),
			integer: ablemessage("com.validate.integer"),
			time: ablemessage("com.validate.time"),
			extension: ablemessage("com.validate.extension"),
			//custom
			filesize: ablemessage("com.validate.filesize"),
			telno: ablemessage("com.validate.telno")
		});
		*/

		$.extend($.validator.messages, {
			//jquery validate
			required: "__FIELD_NAME__ 항목은 필수 입니다.",
			requiredforce: "__FIELD_NAME__ 항목은 필수 입니다.",
			remote: "__FIELD_NAME__ 항목을 수정하세요.",
			email: "__FIELD_NAME__ 항목은 유효하지 않은 E-Mail주소입니다.",
			url: "__FIELD_NAME__ 항목은 유효하지 않은 URL입니다.",
			date: "__FIELD_NAME__ 항목은 올바른 날짜형식으로 입력하세요.",
			dateISO: "__FIELD_NAME__ 항목은 올바른 날짜형식으로 입력하세요. (YYYY-MM-DD)",
			number: "__FIELD_NAME__ 항목은 유효한 숫자로 입력하세요.",
			float: "__FIELD_NAME__ 항목은 소수점 둘째자리까지의 소수만 입력가능합니다.",
			digits: "__FIELD_NAME__ 항목은 숫자만 입력 가능합니다.",
			creditcard: "__FIELD_NAME__ 항목은 올바른 신용카드 번호로 입력하세요.",
			equalTo: "__FIELD_NAME__ 항목값이 동일하지 않습니다.",
			accept: "__FIELD_NAME__ 항목이 올바른 확장자가 아닙니다. (필요한 파일형식: {0})",
			maxlength: $.validator.format("__FIELD_NAME__ 항목은 {0}자를 넘을 수 없습니다."),
			minlength: $.validator.format("__FIELD_NAME__ 항목은 {0}자이상 입력하세요."),
			rangelength: $.validator.format("__FIELD_NAME__ 항목은 문자 길이를 {0}~{1}자로 입력하세요."),
			range: $.validator.format("__FIELD_NAME__ 항목은 {0}~{1} 값으로 입력하세요."),
			max: $.validator.format("__FIELD_NAME__ 항목은 {0} 이하의 값을 입력하세요."),
			min: $.validator.format("__FIELD_NAME__ 항목은 {0} 이상의 값을 입력하세요."),
			//jquery validate additional
			alphanumeric: "__FIELD_NAME__ 항목은 영문자, 숫자, _로만 입력해주세요.",
			lettersonly: "__FIELD_NAME__ 항목은 영문자만 입력해주세요.",
			nowhitespace: "__FIELD_NAME__ 항목은 빈칸없이 입력해주세요.",
			integer: "__FIELD_NAME__ 항목은 숫자로만 입력해주세요.",
			time: "__FIELD_NAME__ 항목은 올바른 시간형식(00:00~23:59)으로 입력해주세요.",
			extension: "__FIELD_NAME__ 항목이 올바른 확장자가 아닙니다. (필요한 파일형식: {0})",
			//custom
			filesize: "__FIELD_NAME__ 항목의 파일크기가 허용크기를 초과합니다. 허용파일크기: {0}",
			userpassword: "__FIELD_NAME__ 항목이 올바른 비밀번호형식이 아닙니다.",
			telno: "__FIELD_NAME__ 항목의 전화번호가 유효하지 않습니다."
		});
	}(jQuery));

	/** CustomValidator */
	//[추가방법]
	//	$.validator.addMethod("newRule", function(value, element) {
	//		return false;
	//	}, "newRule-message");

	jQuery.validator.addMethod("alphanumeric", function(value, element) {
		return this.optional(element) || /^\w+$/i.test(value);
	}, jQuery.validator.messages.alphanumeric);

	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return this.optional(element) || /^[a-z]+$/i.test(value);
	}, jQuery.validator.messages.lettersonly);

	jQuery.validator.addMethod("nowhitespace", function(value, element) {
		return this.optional(element) || /^\S+$/i.test(value);
	}, jQuery.validator.messages.nowhitespace);

	jQuery.validator.addMethod("integer", function(value, element) {
		return this.optional(element) || /^-?\d+$/.test(value);
	}, jQuery.validator.messages.integer);

	jQuery.validator.addMethod("time", function(value, element) {
		return this.optional(element) || /^([01]\d|2[0-3])(:[0-5]\d){1,2}$/.test(value);
	}, jQuery.validator.messages.time);

	jQuery.validator.addMethod("dateISO", function(value, element) {
	//기본 date가 new Date(String)을 이용하면서 IE에서 yyyy/MM/dd만 지원 yyyy/MM/dd는 오류여서 덮어씀.
	//dateISO는 단순시 정규식 NNNN-NN-NN만 검사
		var datePat = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
		var matchArray = value.match(datePat);
		if( value != null || false == _.isEmpty(value)) {
			if( matchArray != null ) {
				var year = matchArray[1];
				var month = matchArray[2];
				var day = matchArray[3];
				if( year < 1900 ){
					return false;
				}
				if( month < 1 || month > 12 || day < 1 || day > 31 ) {
					return false;
				}
				if(( month == 4 || month == 6 || month == 9 || month == 11 ) && day == 31 ) {
					return false;
				}
				//2월달은 28일 또는 29일까지 체크
				if( month == 2 ) {
					var isleap = ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0));
					if(day > 29||(day == 29 && !isleap)){
						return false;
					}
				}
			}
		}
		return this.optional(element) || /^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/.test(value);
	}, jQuery.validator.messages.dateISO);

	// Bad_Master 의 BAD_GOAL 필터링에 사용. 소수만 가능. 소수점 둘째자리까지 Fix ( => 0.XX )
	jQuery.validator.addMethod("float", function(value, element) {
		return this.optional(element) || /^[0]{1}\.\d\d?$/.test(value);
	}, jQuery.validator.messages.float);

	// same as email, but TLD is optional
	jQuery.validator.addMethod("email2", function(value, element, param) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
	}, jQuery.validator.messages.email);

	// same as url, but TLD is optional
	jQuery.validator.addMethod("url2", function(value, element, param) {
		return this.optional(element) || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
	}, jQuery.validator.messages.url);

	// Accept a value from a file input based on a required mimetype
	jQuery.validator.addMethod("accept", function(value, element, param) {
		// Split mime on commas in case we have multiple types we can accept
		var typeParam = typeof param === "string" ? param.replace(/\s/g, '').replace(/,/g, '|') : "image/*",
		optionalValue = this.optional(element),
		i, file;

		// Element is optional
		if (optionalValue) {
			return optionalValue;
		}

		if ($(element).attr("type") === "file") {
			// If we are using a wildcard, make it regex friendly
			typeParam = typeParam.replace(/\*/g, ".*");

			// Check if the element has a FileList before checking each file
			if (element.files && element.files.length) {
				for (i = 0; i < element.files.length; i++) {
					file = element.files[i];

					// Grab the mimetype from the loaded file, verify it matches
					if (!file.type.match(new RegExp( ".?(" + typeParam + ")$", "i"))) {
						return false;
					}
				}
			}
		}

		// Either return true because we've validated each file, or because the
		// browser does not support element.files and the FileList feature
		return true;
	}, jQuery.validator.messages.accept);

	// Older "accept" file extension method. Old docs: http://docs.jquery.com/Plugins/Validation/Methods/accept
	jQuery.validator.addMethod("extension", function(value, element, param) {
		param = typeof param === "string" ? param.replace(/,/g, '|') : "png|jpe?g|gif";
		return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
	}, jQuery.validator.messages.extension);

	///////////////////////////////////////////////////////////////////////////
	// Custom Validator

	// 파일 크기 Validator
	jQuery.validator.addMethod('filesize', function(value, element, param) {
	    // param = size (en bytes)
	    // element = element to validate (<input>)
	    // value = value of the element (file name)
		if(_.isString(param)) {
			param = _.trim(param);
			param = _.rtrim(param, "B");
			param = _.rtrim(param, "Byte");
			param = _.rtrim(param, "Bytes");
			if(_.endsWith(param, "K")) {
				param = _.rtrim(param, "K") * 1024;
			} else if(_.endsWith(param, "M")) {
				param = _.rtrim(param, "M") * 1024 * 1024;
			}  else if(_.endsWith(param, "G")) {
				param = _.rtrim(param, "G") * 1024 * 1024 * 1024;
			}
		}
		try {
			//return this.optional(element) || (element.files[0].size <= param);
			return this.optional(element) || (!_.isEmpty(element[files]) && element.files[0].size <= param);
		} catch (e) {
			return true;
		}
	}, jQuery.validator.messages.filesize);

	//연속된 숫자, 반목된 문자(숫자) 비허용 체크
	function validateBadSequenceLength(pw, badSequenceLength) {
		badSequenceLength = badSequenceLength || 3;
		var numbers = "01234567890";
		var start = badSequenceLength - 1;
		var seq = "_" + pw.slice(0, start);
		for (i = start; i < pw.length; i++) {
			seq = seq.slice(1) + pw.charAt(i);
			if (numbers.indexOf(seq) > -1) {
				console.log('sequencial pw invalid.');
				return false; // sequencial
			}
			if (seq.split(seq.charAt(1)).join("") == "") {
				console.log('equivalant pw invalid.');
				return false; // equivalant
			}
		}
		return true;
	};

	// 사용자 비밀번호 규칙
	jQuery.validator.addMethod("userpassword", function(value, element, param) {
		var validatorConfig = {
			minLengthIfTwoCategoryChar: param.minLengthIfTwoCategoryChar || 10, //2가지 문자사용시 최소 자리수
			minLengthIfThreeCategoryChar: param.minLengthIfTwoCategoryChar || 8, //3가지 문자사용시 최소 자리수
			badSequenceLength: param.badSequenceLength || 3 //연속된 또는 같은 숫자 비허용 길이 (Sequencial, Equvalant)
		};
		validatorConfig.validCheckFn = param.validCheckFn || function(value, result, config) {
			//2가지 조합 이상이면서 10자리 이상이면 true
			//3가지 조합 이상이면서 8자리 이상이면 true
			var isValid = (result.charCategoryCount >= 2 && result.inputLength >= config.minLengthIfTwoCategoryChar) ||
						  (result.charCategoryCount >= 3 && result.inputLength >= config.minLengthIfThreeCategoryChar);
			if(!isValid) {
				console.log('pw char types or length invalid.');
			}
			//연속된 숫자, 반목된 문자(숫자) 비허용
			isValid = isValid && validateBadSequenceLength(value, config.badSequenceLength);
			return isValid;
		};
		console.log("userpassword validatorConfig", validatorConfig);

		var checkResult = {
			inputLength: _.size(value),
			includeLetter: /[a-z]+/i.test(value),
			includeLowerLetter: /[a-z]+/.test(value),
			includeUpperLetter: /[A-Z]+/.test(value),
			includeDigit: /\d+/i.test(value),
			includeSpecialChar: /[~!@\#$%<>^&*\()\-=+_\']+/i.test(value) //Sync with EzStringUtil
		};
		checkResult.charCategoryCount = 0; //문자 조합수 카운트
		checkResult.charCategoryCount += checkResult.includeLetter ? 1 : 0;
		checkResult.charCategoryCount += checkResult.includeDigit ? 1 : 0;
		checkResult.charCategoryCount += checkResult.includeSpecialChar ? 1 : 0;
		console.log("password checkResult ==> ", checkResult);

		var isValid = validatorConfig.validCheckFn(value, checkResult, validatorConfig);
		return this.optional(element) || isValid;
	}, jQuery.validator.messages.userpassword);

	jQuery.validator.addMethod("telno", function(value, element) {
		return this.optional(element) || /^[0-9-+]+$/.test(value);
	}, jQuery.validator.messages.telno);
});

