/**
 * ablecoms underscore js library extensions
 *
 * @author Min ByeongDon <deepfree@gmail.com>
 */
$(function() {

	/**
	 * [Fix]
	 * 현재버전에서 isFunction 오류시 추가 검토 필요
	 * ie에서 객체의 함수를 담은 변수(예: obj.fn)가 isFunction이 아니라고 응답하는 오류로 wrap
	 */
	/*_.isFunction = _.wrap(_.isFunction, function(originalFunc, obj) {
		if(obj != null && obj != undefined && _.string.startsWith(obj.toString(), 'function')) {
			return true;
		}
		return originalFunc(obj);
	});*/


	/**
	 * [Fix]
	 * isEmpty가 Array,String,Object만 지원해서,
	 * isEmpty(Number)가 true로 반환되는 것을 Number이면 false를 반환하도록 수정.
	 */
	_.isEmpty = _.wrap(_.isEmpty, function(originalFunc, obj) {
		if(_.isNumber(obj)) {
			return false;
		}
		return originalFunc(obj);
	});

	/**
	 * sum: 집계 Helper Function
	 *   _.sum(list[, function(item) { return item.price; }])
	 *   _.sum(list[, "price" ])
	 */
	_.sum = function(list, iterator) {
		var _iterator = iterator;
		if(_.isString(iterator)) {
			_iterator = function(item) { return item[iterator]; };
		}
		_iterator = _iterator || function(item) { return item; };
		return _.reduce(list, function(sum, item) {
			return sum + _iterator(item);
		}, 0);
	};

	/**
	 * groupBySum: 그룹별 집계 Helper Function
	 *   _.groupBySum(list, function(item) { return item.key; }, function(item) { return item.price; })
	 *   _.groupBySum(list, "key", "price")
	 */
	_.groupBySum = function(list, keyIterator, sumIterator) {
		var _keyIterator;
		if(_.isFunction(keyIterator)) {
			_keyIterator = keyIterator;
		} else if(_.isString(keyIterator)) {
			_keyIterator = function(item) { return item[keyIterator]; };
		}
		var _sumIterator;
		if(_.isFunction(sumIterator)) {
			_sumIterator = sumIterator;
		} else if(_.isString(sumIterator)) {
			_sumIterator = function(item) { return item[sumIterator]; };
		}
		var result = _.reduce(list, function(result, item) {
			var key = _keyIterator(item);
			if(!_.ezContainKeys(result, key)) {
				result[key] = 0;
			}
			result[key] = result[key] + _sumIterator(item);
			return result;
		}, {});
		return result;
	};

});