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
	 * Moment Binding Handler: moment.js 를 이용한 날짜/시간 Formatting지원
	 * momentYYYYMMDDHHMM: 기본 format - 'YYYY-MM-DD HH:mm'
	 *   data-bind="momentYYYYMMDDHHMM:dateField"
	 */
	ko.bindingHandlers.momentYYYYMMDDHHMM = {
	    //update: createMomentBindingHandler('YYYY-MM-DD HH:mm') //20140805 현업요구로 초단위 추가
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
	        	ko.utils.setTextContent(element, val);
	        }catch(e) {
	        	$(element).text(val);
	        }
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

}());

