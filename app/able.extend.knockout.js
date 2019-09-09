/**
 * ablecoms knockout js library extensions
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
(function() {

	//////////////////////////////////////////////////
	// Global

	/**
	 * 모든 value binding에 valueUpdate="afterkeydown"을 자동으로 추가:
	 *   vm의 값이 갱신되지 않아 기존 값이 참조되는 현상을 제거. enterKey 바인딩과 함께 사용하면 유용함
	 */
	(function () {
	    var getInjectValueUpdate = function (allBindings) {
	        return {
	            has: function (bindingKey) {
	                return (bindingKey == 'valueUpdate') || allBindings.has(bindingKey);
	            },
	            get: function (bindingKey) {
	                var binding = allBindings.get(bindingKey);
	                if (bindingKey == 'valueUpdate') {
	                    binding = binding ? [].concat(binding, 'afterkeydown') : 'afterkeydown';
	                }
	                return binding;
	            }
	        };
	    };

	    var valueInitHandler = ko.bindingHandlers.value.init;
	    ko.bindingHandlers.value.init = function (element, valueAccessor, allBindings, viewModel, bindingContext) {
	        return valueInitHandler(element, valueAccessor, getInjectValueUpdate(allBindings), viewModel, bindingContext);
	    };
	}());

	//////////////////////////////////////////////////
	//[fn]

	/**
	 * A filtered view of an observable array
	 * @see http://knockoutjs.com/documentation/fn.html
	 */
	ko.observableArray.fn.filterByProperty = function(propName, matchValueOrObservable) {
	    return ko.computed(function() {
	        var allItems = this();
	        var matchingItems = new Array(); //[]; //for stupid eclipe
	        var matchValue = (ko.isObservable(matchValueOrObservable)) ? matchValueOrObservable() : matchValueOrObservable;
	        for (var i = 0; i < allItems.length; i++) {
	        	//var current = allItems[i];
	            var current = _.clone(allItems[i]);
	            if (ko.unwrap(current[propName]) === matchValue)
	                matchingItems.push(current);
	        }
	        return matchingItems;
	    }, this);
	};

	/**
	 * A filtered view of an observable array
	 * @author Min ByeongDon <deepfree@gmail.com>
	 */
	ko.observableArray.fn.filterByFn = function(fnPredict) {
		if(!_.isFunction(fnPredict)) {
			console.error("To use filterByFn, predict function required.")
		}
	    return ko.computed(function() {
	        var allItems = this();
	        var matchingItems = new Array(); //[]; //for stupid eclipe
	        for (var i = 0; i < allItems.length; i++) {
	        	//var current = allItems[i];
	            var current = _.clone(allItems[i]);
	            if (fnPredict(current, i, allItems))
	                matchingItems.push(current);
	        }
	        return matchingItems;
	    }, this);
	};

	//////////////////////////////////////////////////
	//[binding handler]

	/**
	 * Enter키 이벤트 처리를 위한 Binding Handler
	 *   data-bind="enterKey:customFunction"
	 */
	ko.bindingHandlers.enterKey = {
	    init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
	        var allBindings = allBindingsAccessor();
	        $(element).keypress(function (event) {
	            var keyCode = (event.which ? event.which : event.keyCode);
	            if (keyCode === 13) {
	            	if(_.has(allBindings, 'value')) {
	            		allBindings.value($(element).val()); //set the value of the observable early because often the function you call on the next line wants to reference it
	            	}
	                allBindings.enterKey.call(viewModel);
	                return false;
	            }
	            return true;
	        });
	    }
	};

	/**
	 * Change 이벤트 처리를 위한 Binding Handler
	 *   data-bind="change:customFunction"
	 */
	ko.bindingHandlers.change = {
	    init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
	        var allBindings = allBindingsAccessor();
	        $(element).change(function (event) {
	            allBindings.change.call(viewModel, event);
                return false;
	        });
	    }
	};

	/** Moment Binding Handler Factory */
	var createMomentBindingHandler = function(momentFormat) {
		var _momentFormatClojure = momentFormat || 'YYYY-MM-DD HH:mm:ss';
		var handler = function (element, valueAccessor, allBindingsAccessor, viewModel) {
	        var value = valueAccessor();
	        var allBindings = allBindingsAccessor();
	        var valueUnwrapped = ko.utils.unwrapObservable(value);

	        // Date formats: http://momentjs.com/docs/#/displaying/format/
	        var pattern = allBindings.momentFormat || _momentFormatClojure;

	        var output = "";
	        if (valueUnwrapped !== null && valueUnwrapped !== undefined && valueUnwrapped.length > 0) {
	            output = moment(valueUnwrapped).format(pattern);
	        }

	        if ($(element).is("input") === true) {
	            $(element).val(output);
	        } else {
	            $(element).text(output);
	        }
	    };
	    return handler;
	};
	/**
	 * Moment Binding Handler: moment.js 를 이용한 날짜/시간 Formatting지원
	 * momentISO: 기본 format - 'YYYY-MM-DD'
	 *   data-bind="momentISO:dateField"
	 */
	ko.bindingHandlers.momentISO = {
	    update: createMomentBindingHandler('YYYY-MM-DD')
	};
	/**
	 * Moment Binding Handler: moment.js 를 이용한 날짜/시간 Formatting지원
	 * moment: 기본 format - 'YYYY-MM-DD HH:mm:ss'
	 *   data-bind="moment:dateField"
	 *   data-bind="moment:dateField, momentFormat:'커스텀 moment 포맷'"
	 */
	ko.bindingHandlers.moment = {
	    update: createMomentBindingHandler('YYYY-MM-DD HH:mm:ss')
	};

	/**
	 * Textarea에서 입력한 문자열을 br 태그로 치완하여 뿌리는 렌더러
	 */
	ko.bindingHandlers['brtext'] = {
		'init': function() {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
			var outputBr = valueAccessor().replace(/\n/g,"<br/>");
			outputBr = outputBr.replace(/</g,"&lt;");
        	outputBr = outputBr.replace(/>/g,"&gt;");
        	outputBr = outputBr.replace(/&lt;br\x2f&gt;/g,"<br/>");
	        ko.utils.setHtml(element, outputBr);
	        //ko.utils.setTextContent(element, valueAccessor().replace(/\n/g,"<br/>"));
	    }
	};

	/** Y/N => Yes, No 핸들러 */
	ko.bindingHandlers['yntext'] = {
		'init': function() {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
	    	var val = valueAccessor();
	    	var valLower = val.toLowerCase();
	    	val = (valLower == "y" || valLower == "t" || valLower == "true" || valLower == "yes") ? "Yes" : "No";
	        try {
	        	ko.utils.setTextContent(element, val);
	        }catch(e) {
	        	$(element).text(val);
	        }
	    }
	};

	/** numberFormat 핸들러
	 * 10000 => 10,000
	 */
	ko.bindingHandlers['numberFormat'] = {
		'init': function() {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
	    	var val = valueAccessor();
	    	val = _.isNumber(val) ? _.string.numberFormat(val) : val;
	    	try {
	    		if( element.tagName == "INPUT") {
					element.value = val;
	    		} else {
	    			ko.utils.setTextContent(element, val);
	    		}
	        }catch(e) {
	        	$(element).text(val);
	        }
	    }
	};

	/** numberFormat 핸들러 2way binding...
	  *  10000 => 10,000
	  *  사용법 : <input type="text" data-bind="numberFormat2: {val: price, max: 500000, maxOverErrorMsg: maxOverErrorMsg, placeholder: placeholder}"/>
	  *            <input type="text" data-bind="numberFormat2: {val: price}"/>
	  *  val(required=true) : 바인딩 할 값
	  *  max(required=false) : 최대값
	  *  maxOverErrorMsg(required=false) : 최대값을 초과할 경우 보여줄 메시지
	  *  placeholder(required=false) : 값이 없을 경우 보여질 placeholder 메시지
	  */
	 ko.bindingHandlers['numberFormat2'] = {
	  'init': function(element, valueAccessor) {
	   // Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
	   // It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	   $(element).bind("input", function() {
	    var valueAcc = valueAccessor();
	    valueAcc.val($(element).val());
	   });
	   $(element).change(function(event) {
	    var valueAcc = valueAccessor();
	    valueAcc.val($(element).val());
	   });
	  },
	     'update': function (element, valueAccessor, allBindingsAccessor, viewModel) {
	      // setHtml will unwrap the value if needed
	      // valueAccessor : { val: '', max: '', maxOverErrorMsg: '' }
	      var valueAcc = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	      _.defaults(valueAcc, { max: 999999999999999, maxOverErrorMsg: "", placeholder: "" });
	      var val = valueAcc.val;
	      var max = valueAcc.max;
	      var msg = valueAcc.maxOverErrorMsg;
	      var placeholder = valueAcc.placeholder;
	      if( !ko.isObservable(val) ) {
	       throw "numberFormat2 바인딩 변수는 observable 객체여야합니다."
	      }

	      //Number.MAX_SAFE_INTEGER 값(9007199254740991)을 초과하면 기본값으로 설정
	      if(max > Number.MAX_SAFE_INTEGER) {
	       max = 999999999999999;
	      }

	      var result; //binding 할 값
	      if(_.isNumber(val())) {
	       //숫자일 경우 replace하기위해 문자열로 변환
	    result = val().toString();
	      } else {
	       if(!_.isEmpty(val())) {
	     result = val();
	       } else {
	        //empty면 placeholder 표시
	        result = "";
	     element.placeholder = placeholder;
	     return;
	       }
	      }
	      //문자열에서 숫자만 추려낸 뒤 Number로 변환
	      result = _.toNumber(result.replace(/[^0-9]/g, ''));

	      if( result > max ) { //MAX값 초과 시
	       if( !_.isEmpty(msg)) {
	        alert(msg);
	       }
	       result = max;
	      }
	      val(result); //binding된 값에 할당

	      //formating 된 문자열을 element 값으로 적용
	      var formattedResult = _.isNaN(result) ? 0 : _.string.numberFormat(result);
	      try {
	       if( element.tagName == "INPUT") {
	     element.value = formattedResult;
	       } else {
	        ko.utils.setTextContent(element, formattedResult);
	       }
	         }catch(e) {
	          $(element).text(formattedResult);
	         }
	     }
	 };

	/** Y/N => Yes, No 핸들러 */
	ko.bindingHandlers['yntext'] = {
		'init': function() {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
	    	var val = valueAccessor();
	    	var valLower = val.toLowerCase();
	    	val = (valLower == "y" || valLower == "t" || valLower == "true" || valLower == "yes") ? "Yes" : "No";
	        try {
	        	ko.utils.setTextContent(element, val);
	        }catch(e) {
	        	$(element).text(val);
	        }
	    }
	};

	/**
	 * TextInput을 Kendo datePicker로 렌더링
	 */
	ko.bindingHandlers.datePicker = {
		'init': function(element) {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
			var datePicker = $(element).kendoDatePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd"
			}).data("kendoDatePicker");
			datePicker.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
			$(element).attr("readonly", true);
			$(element).on("keydown", function(event) {
				event.preventDefault();
				if( event.keyCode == 46) {
					$(element).data("kendoDatePicker").value('');
				}
			});
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	var value = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var date = ko.isObservable(value) ? ko.unwrap(value) : value;
	    	date = _.isEmpty(date) ? "" : moment(date).format('YYYY-MM-DD');

			$(element).data("kendoDatePicker").value(date);
	    }
	};

	/**
	 * TextInput을 Kendo rangeDatePicker(시작-종료)로 렌더링
	 * rangeDatePicker는 시작input에만 바인딩, 종료input은 옵션에 설정한 endElementId로 종료Input을 검색해 자동으로 바인딩처리
	 *
	 *	[ Option ] ==> {value: searchStartDate, endElementId: 'searchEndDate', endValue: searchEndDate}
	 * @searchStartDate : 검색시작일 input에 knockout binding 되어있는 값
	 * @endElementId    : 검색종료일 input id
	 * @endValue		: 검색종료일 input에 knockout binding 되어있는 값
	 * @isDeletable     : 삭제가능여부 (DEL키 바인딩)
	 */
	ko.bindingHandlers.rangeDatePicker = {
		'init': function(element, valueAccessor) {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
			var option = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
			var startElement = $(element);
			var endElement = $("#"+ option.endElementId);
			var isDeletable = option.isDeletable==true ? true : false;

			var start = startElement.kendoDatePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd",
	    		change: function() { kendoRangeDateChangeEventHanlder("start", start, end); }
			}).data("kendoDatePicker");
			var end = endElement.kendoDatePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd",
	    		change: function() { kendoRangeDateChangeEventHanlder("end", start, end); }
			}).data("kendoDatePicker");
			start.max(ko.isObservable(option.endValue)? ko.unwrap(option.endValue) : option.endValue);
			end.min(ko.isObservable(option.value)? ko.unwrap(option.value) : option.value);
			startElement.attr("readonly", true);
			endElement.attr("readonly", true);
			if( isDeletable ) {
				start.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
				end.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
				startElement.on("keydown", function(event) {
					event.preventDefault();
					if( event.keyCode == 46 ) {
						startElement.data("kendoDatePicker").value('');
						kendoRangeDateChangeEventHanlder("start", start, end);
					}
				});
				endElement.on("keydown", function(event) {
					event.preventDefault();
					if( event.keyCode == 46) {
						endElement.data("kendoDatePicker").value('');
						kendoRangeDateChangeEventHanlder("end", start, end);
					}
				});
			}
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	var option = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;

			var startDate = ko.isObservable(option.value) ? ko.unwrap(option.value) : option.value;
	    	startDate = _.isEmpty(startDate) ? "" : moment(startDate).format('YYYY-MM-DD');
	    	var endDate = ko.isObservable(option.endValue) ? ko.unwrap(option.endValue) : option.endValue;
	    	endDate = _.isEmpty(endDate) ? "" : moment(endDate).format('YYYY-MM-DD');

	    	$(element).data("kendoDatePicker").value(startDate);
	    	$("#"+ option.endElementId).data("kendoDatePicker").value(endDate);
	    }
	};

	/**
	 * TextInput을 Kendo rangeDateTimePicker(시작-종료)로 렌더링
	 * rangeDateTimePicker는 시작input에만 바인딩, 종료input은 옵션에 설정한 endElementId로 종료Input을 검색해 자동으로 바인딩처리
	 *
	 *	[ Option ] ==> {value: searchStartDate, endElementId: 'searchEndDate', endValue: searchEndDate}
	 * @searchStartDate : 검색시작일 input에 knockout binding 되어있는 값
	 * @endElementId    : 검색종료일 input id
	 * @endValue		: 검색종료일 input에 knockout binding 되어있는 값
	 * @isDeletable     : 삭제가능여부 (DEL키 바인딩)
	 */
	ko.bindingHandlers.rangeDateTimePicker = {
		'init': function(element, valueAccessor, allBindings) {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
			var option = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
			var startElement = $(element);
			var endElement = $("#"+ option.endElementId);
			var isDeletable = option.isDeletable==true ? true : false;

			var start = startElement.kendoDateTimePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd HH:mm:ss",
	    		change: function() { kendoRangeDateChangeEventHanlder("start", start, end); }
			}).data("kendoDateTimePicker");
			var end = endElement.kendoDateTimePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd HH:mm:ss",
	    		change: function() { kendoRangeDateChangeEventHanlder("end", start, end); }
			}).data("kendoDateTimePicker");
			start.max(ko.isObservable(option.endValue)? ko.unwrap(option.endValue) : option.endValue);
			end.min(ko.isObservable(option.value)? ko.unwrap(option.value) : option.value);
			startElement.attr("readonly", true);
			endElement.attr("readonly", true);
			if( isDeletable ) {
				start.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
				end.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
				startElement.on("keydown", function(event) {
					event.preventDefault();
					if( event.keyCode == 46 ) {
						startElement.data("kendoDateTimePicker").value('');
						kendoRangeDateChangeEventHanlder("start", start, end);
					}
				});
				endElement.on("keydown", function(event) {
					event.preventDefault();
					if( event.keyCode == 46) {
						endElement.data("kendoDateTimePicker").value('');
						kendoRangeDateChangeEventHanlder("end", start, end);
					}
				});
			}

			if(allBindings != null && allBindings()['enable'] === false){
				$(startElement).data('kendoDateTimePicker').enable(false);
				$(endElement).data('kendoDateTimePicker').enable(false);
			}

	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	var option = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;

			var startDate = ko.isObservable(option.value) ? ko.unwrap(option.value) : option.value;
	    	startDate = _.isEmpty(startDate) ? "" : moment(startDate).format('YYYY-MM-DD HH:mm:ss');
	    	var endDate = ko.isObservable(option.endValue) ? ko.unwrap(option.endValue) : option.endValue;
	    	endDate = _.isEmpty(endDate) ? "" : moment(endDate).format('YYYY-MM-DD HH:mm:ss');

	    	$(element).data("kendoDateTimePicker").value(startDate);
	    	$("#"+ option.endElementId).data("kendoDateTimePicker").value(endDate);
	    }
	};

	/**
	 * TextInput을 Kendo dateTimePicker로 렌더링
	 */
	ko.bindingHandlers.dateTimePicker = {
		'init': function(element, valueAccessor) {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.

			var dateTimePicker = $(element).kendoDateTimePicker({
	    		culture: "ko-KR",
	    		format: "yyyy-MM-dd HH:mm:ss"
			}).data("kendoDateTimePicker");
			dateTimePicker.wrapper.attr("title", "클릭 후 DEL 키를 누르면 삭제됩니다.");
			$(element).attr("readonly", true);
			$(element).on("keydown", function(event) {
				event.preventDefault();
				if( event.keyCode == 46) {
					dateTimePicker.value('');
				}
			});
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	var value = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var dateTime = ko.isObservable(value) ? ko.unwrap(value) : value;
	    	dateTime = _.isEmpty(dateTime) ? "" : moment(dateTime).format('YYYY-MM-DD HH:mm:ss');

	    	$(element).data("kendoDateTimePicker").value(dateTime);
	    }
	};
	/** KendoRangePicker에서 사용하는 날짜 역전방지 핸들러 */
	function kendoRangeDateChangeEventHanlder(type, start, end) {
		var startDate = start.value();
		var endDate = end.value();
		//Kendo Default ==> min: new Date(1900,0,1), max : new Date(2099,11,31)
		var endMax = _.isNull(endDate) ? new Date(2099,11,31) : moment(endDate).format("YYYY-MM-DD HH:mm:ss");
		var startMin = _.isNull(startDate) ? new Date(1900,0,1) : moment(startDate).format("YYYY-MM-DD HH:mm:ss");
		start.max(endMax);
		end.min(startMin);
	}

	/** Y/N checkbox checked 바인딩 핸들러 */
	ko.bindingHandlers['YNChecked'] = {
		'init': function(element, valueAccessor) {
			var val = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var value = ko.unwrap(val);
	        $(element).prop('checked', value=='Y').change(function() {
	        	if(ko.isObservable(val)) {
	        		val(this.checked?'Y':'N');
	        	}
	        });
	        // Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
	    	var val = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var value = ko.isObservable(val) ? val() : val;
	        $(element).prop('checked', value=='Y');
	    }
	};

	/** Boolean checkbox checked 바인딩 핸들러 */
	ko.bindingHandlers['BooleanChecked'] = {
		'init': function(element, valueAccessor) {
			var val = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var value = ko.unwrap(val);
	        $(element).prop('checked', value === true).change(function() {
	        	if(ko.isObservable(val)) {
	        		val(this.checked? true : false );
	        	}
	        });
	        // Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	// setHtml will unwrap the value if needed
	    	var val = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var value = ko.isObservable(val) ? val() : val;
	        $(element).prop('checked', value=== true);
	    }
	};

	/**
	 * jQuery UI dialog 초기화 Binding Handler
	 *
	 * @see http://stackoverflow.com/questions/8611327/integrating-jquery-ui-dialog-with-knockoutjs
	 */
	ko.bindingHandlers.jqDialog = {
		/*
		[사용방법]
		<div id="detailsDialog" title="Details" data-bind="jqDialog: { 'autoOpen': false, 'resizable': false, 'modal': true, 'width': '60%' }, jqDialogVisible: detailsVM">
			<div>
				...
			</div>
		</div>
		*/
	    init: function (element, valueAccessor) {
	        var options = ko.utils.unwrapObservable(valueAccessor()) || {};
        	//do in a setTimeout, so the applyBindings doesn't bind twice from element being copied and moved to bottom
	        setTimeout(function() {
	        	/*
	        	options.close = function() {
                    allBindingsAccessor().jqDialogVisible(false); //현재는 모델의 존재여부에 Binding하고 있어서 Setting 처리 제외
                };
                */
	        	$(element).dialog(options);
	        }, 0);
	        //handle disposal (not strictly necessary in this scenario)
	        ko.utils.domNodeDisposal.addDisposeCallback(element, function () {
	            $(element).dialog("destroy");
	        });
	    },
	    update: function(element, valueAccessor, allBindingsAccessor) {
            var shouldBeOpen = ko.utils.unwrapObservable(allBindingsAccessor().jqDialogVisible);
            var $el = $(element);
            var dialog = $el.data("uiDialog") || $el.data("dialog");
            //don't call open/close before initilization
            if (dialog) {
                $el.dialog(shouldBeOpen ? "open" : "close");
            }
        }
	};

	/**
	 * Select2 jqueryControl 초기화 Binding Handler
	 * <div data-bind="value: selectedState, select2: { data: states, placeholder: 'Select a State', formatResult: format }" class="select2" style="width: 200px"></div>
	 * Selected: <span data-bind="text: selectedState"></span>
	 * @see http://ivaynberg.github.io/select2/
	 */
	ko.bindingHandlers.select2 = {
	    init: function(el, valueAccessor, allBindingsAccessor, viewModel) {
	      ko.utils.domNodeDisposal.addDisposeCallback(el, function() {
	        $(el).select2('destroy');
	      });

	      var allBindings = allBindingsAccessor(),
	          select2 = ko.utils.unwrapObservable(allBindings.select2);

	      $(el).select2(select2);
	    },
	    update: function (el, valueAccessor, allBindingsAccessor, viewModel) {
	        var allBindings = allBindingsAccessor();

	        if ("value" in allBindings) {
	            $(el).select2("data", allBindings.value());
	        } else if ("selectedOptions" in allBindings) {
	            var converted = [];
	            var textAccessor = function(value) { return value; };
	            if ("optionsText" in allBindings) {
	                textAccessor = function(value) {
	                    var valueAccessor = function (item) { return item; }
	                    if ("optionsValue" in allBindings) {
	                        valueAccessor = function (item) { return item[allBindings.optionsValue]; }
	                    }
	                    var items = $.grep(allBindings.options(), function (e) { return valueAccessor(e) == value});
	                    if (items.length == 0 || items.length > 1) {
	                        return "UNKNOWN";
	                    }
	                    return items[0][allBindings.optionsText];
	                }
	            }
	            $.each(allBindings.selectedOptions(), function (key, value) {
	                converted.push({id: value, text: textAccessor(value)});
	            });
	            $(el).select2("data", converted);
	        }
	    }
	};

	/** [WAS 업무용] 카드번호 => Dashed 카드번호 */
	ko.bindingHandlers['cardno'] = {
		'init': function() {
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	var val = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
	    	var value = ko.isObservable(val) ? val() : val;
        	$(element).text(val.replace(/^(\d{4})(\d{4})(\d{4})(\d{4})$/gi, '$1-$2-$3-$4'));
	    }
	};

	/**
	 * 	textarea를 KendoEditor 로 렌더링
	 * 	@author impjs
	 *  사용방법 : <textarea data-bind="value: "바인딩 할 값", kendoEditor: editable여부"></textarea>
	 *  참고사항 : textarea에 직접 바인딩이 되어있어도, 실제 UI에서 입력하는 곳은 iframe입니다.
	 *  		  editor(iframe)에서 focus가 해제되는 순간 textarea로 값이 전달 됩니다. (UI에서 처리되는게 아니라 update를 타지않음)
	 */
	ko.bindingHandlers['kendoEditor'] = {
		'init': function(element, valueAccessor, propertyWriters) {
			// Prevent binding on the dynamically-injected text node (as developers are unlikely to expect that, and it has security implications).
			// It should also make things faster, as we no longer have to consider whether the text node might be bindable.
			valueAccessor = (_.isFunction(valueAccessor)) ? valueAccessor() : valueAccessor;
			propertyWriters = (_.isFunction(propertyWriters)) ? propertyWriters() : propertyWriters;

			//Editable 모드 확인
			var isEditable = valueAccessor;
			if( ko.isObservable(isEditable) || ko.isComputed(isEditable)) {
				isEditable = isEditable();
			}
			var tools = ["bold", "italic", "underline", "strikethrough", "subscript", "superscript",
						"fontName", "fontSize", "foreColor", "backColor", "justifyLeft", "justifyCenter", "justifyRight", "justifyFull",
						"insertUnorderedList", "insertOrderedList", "indent", "outdent", "createLink", "unlink",
						"createTable", "addColumnLeft", "addColumnRight", "addRowAbove", "AddRowBelow", "deleteRow", "deleteColumn",
						"formatting", "cleanFormatting", "viewHtml" ];
			$(element).kendoEditor({
				resizable : {
					content: true,
					toolbar: isEditable
				},
				tools: isEditable ? tools : [],
				change: onChangeEditor
			});

			var editor = $(element).data("kendoEditor");
			editor.body.setAttribute("contenteditable", isEditable);

			//UPDATE Handler
			function onChangeEditor() {
				if( ko.isObservable(propertyWriters.value) ) {
					propertyWriters.value(editor.value());
				} else {
					//바인딩 된 값이 Observable이 아닐 경우 _ko_property_writers를 통해 값 변경
					propertyWriters._ko_property_writers.value(editor.value());
				}
			}
	        return { 'controlsDescendantBindings': true };
		},
	    'update': function (element, valueAccessor) {
	    	//textarea를 조작하는것이 아니라 update를 인지하지 못함
	    	//update는 onChangeEditor에서 인지함
	    }
	};
}());

