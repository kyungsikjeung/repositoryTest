/**
 * AbleKendoGridVM
 * elementId : Grid HtmlElement ID. ex: "#grid"
 * dataUrl: REST API URL ex: "/admin/api/test/page.json"
 * columns: Grid columns Setting ex: [{fiedl:'mainCode', title:'메인코드'}, {fiedl:'subCode', title:'서브코드'}...]
 * eventHandler: Grid Event Callback Funcion
 * applySearchCondition: function to apply search condition when grid load
 * [사용법]
 * new AbleKendoGridVM("#grid", self.listURL, self.gridColumns, self.gridEventListener, self.applySearchCondition);
 */
function AbleKendoGridVM(elementId, url, columns, eventHandler, applySearchCondition) {
	var self = this;
	self.clazz = "AbleKendoGridVM";

	//AutoWidth컬럼 용 변수
	var autoWidthColumnIdx;
	var fixedWidth;
	var minWidth = 100;
	var scrollbarWidth = 19;

    //Grid column align
    var gridColumnAlign = {
        left: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-left" } },
        center: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-center" } },
        right: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-right" } }
    };

    //Able Column Config
    function extendColumnsAlign(columns) {
    	return _.chain(columns).map(_.clone)
				.map(function(item) {
					if(_.has(item, 'ezAlign')) {
						if(_.has(item, "columns")) {
							_.each(item.columns, function(subItem) {
								_.extend(subItem, gridColumnAlign[subItem.ezAlign]);
							});
						}
						_.extend(item, gridColumnAlign[item.ezAlign]);
					}
					if(_.has(item, "minWidth")) { //authWidth컬럼의 경우 minWidth값을 주면 해당값이 최소값으로 적용됨
						minWidth = item.minWidth;
					}
					return item;
				}).value();
    }
    columns = extendColumnsAlign(columns);

	//그리드 Element
	self.gridElement = $(elementId);

	//List API Url
	self.listURL = url;

	//Grid 이벤트 핸들러
	self.eventHandler = eventHandler || function(){};
	//검색조건 적용 함수
	self.applySearchCondition = applySearchCondition || function(param) { return param; };

	//Sort VM  (sort: [{field, dir}])
	var prepareSortCondition = function(sort) {
		var sortValues = _.map(sort, function(item) { return $.format("{0},{1}", item.field, item.dir) });
		if(_.isEmpty(sortValues)) {
			return null;
		}
		return { sort: sortValues };
	};

	//Row 선택 시 선택된 Row의 Data를 저장
	self.selectedRowItem = null;

	//Kendo Data Source Options
	self.dataSourceOptions = {
		transport: {
			read: {
				url: self.listURL,
				type: "GET",
				contentType: "application/json",
				dataType: "json",
				complete: function(response) {
				}
			},
			parameterMap: function(data, type) {
				if( type == "read" ) {
					var ableGridParameter = {
						page: data.page - 1,
						size: data.pageSize
					};
					//검색조건 적용
					ableGridParameter = self.applySearchCondition(ableGridParameter);
					//Sort 적용
					if(data.sort) {
						ableGridParameter = _.extend(ableGridParameter, prepareSortCondition(data.sort));
					}
					return $.param(ableGridParameter);
				}
			},
			success: function(data) {
				ezUtil.checkRESTResponseMsg(data);
			}
		},
		schema: {
			parse: function(response) {
				if(ezUtil.checkRESTResponseMsg(response)) {
					var parsedData = {
						data: response.body.content,
						total: response.body.totalElements
					};
					return parsedData;
				} else {
					return false;
				}
			},
			data: function(parsedData) {
				return parsedData.data;
			},
			total: function(parsedData) {
				return parsedData.total;
			}
		},
		pageSize: 10,
		serverPaging: true,
		serverSorting: true,
		change: function(event) {
			if ( !_.isEmpty(self.dataSource._sort) ){
				var sortObj = self.dataSource._sort;
				var fieldNm = sortObj[0]['field'];
				var dir = sortObj[0]['dir'];


//				event.items.reverse();
//				if ( event.items[0].hasOwnProperty(fieldNm) != null ){
//					event.items = _.sortBy(event.items, function(row) { return row[fieldNm];  });
//					if (  dir != 'asc' ){
//						event.items = event.items.reverse();
//					}
//				}
			}

			self.selectedRowItem = null;
        	self.eventHandler("dataSourceChanged", null);
        }
	};
	self.dataSource = new kendo.data.DataSource(self.dataSourceOptions);

	self.gridOptions = {
		//toolbar: ["excel"],
		autoBind: false,
		dataSource: self.dataSource,
		height: 370,
		sortable: false,
		pageable: {
			refresh: true,
			previousNext: true,
			buttonCount: 10,
			pageSize: 10,
			pageSizes: appconfig.allowPageSizeList
		},
		resizable: true,
		columns: columns,
        selectable: "row",
        change: function(event) {
        	self.selectedRowItem = event.sender.dataItems()[this.select().index()];
        	self.eventHandler("rowSelected", self.selectedRowItem);
        },
        columnResize: function(e) {
        	self.removeResizeEvent();
        },
        noRecords: true,
        messages: {
        	noRecords: "검색된 데이터가 없습니다."
        }
	};
	self.kendoGrid = self.gridElement.kendoGrid(self.gridOptions);


	/**
	 *	Grid min-width Settings
	 *	그리드 컬럼 width를 설정하지 않으면 autoWidth로 처리되는데,
	 *	그리드 사이즈가 줄어들면 autoWidth 컬럼의 사이즈는 0까지 줄어들어 화면에 표시되지 않는 이슈가 있어 autoWidth컬럼의 사이즈를 동적으로 변경
	 *
	 *  fixedWidth: 컬럼 width가 정해진 컬럼들의 width 합계
	 *  autoWidthColumnIdx: 컬럼 width가 정해지지 않은 컬럼의 index
	 *	minWidth: 최소값=100, 컬럼설정에서 minWidth값을 주면 해당값으로 설정됨
	 */
	//window Resize Event에 여러개의 핸들러를 붙이기 위해 resize이벤트에 커스텀네임을 붙여 다른 이벤트로 인식하게 만듬
	//화면에 그리드가 여러개 있는 경우...
	var resizeEventName = "resize."+elementId.substr(1);

	//컬럼 사이즈 변경
	self.gridAuthWidthResize = function() {
		var grid = self.gridElement.data("kendoGrid");
		if( !_.isEmpty(grid)) {
			var totalWidth = grid.getSize().width;
			var authWidth = totalWidth - fixedWidth - scrollbarWidth;
			//console.log("authWidth: {}, minWidth: {} ",authWidth, minWidth);
			authWidth = (authWidth < minWidth ) ? minWidth : authWidth;
			var headerCol = grid.thead.parent().find("> colgroup > col:eq("+ autoWidthColumnIdx +")");
			var bodyCol = grid.table.find("> colgroup > col:eq("+ autoWidthColumnIdx +")");

			$(headerCol).width(authWidth);
			$(bodyCol).width(authWidth);

			grid.resize();
		}
	};

	//autoWidth컬럼 검색
	self.findAuthWidthColumn = function() {
		autoWidthColumnIdx = null;

		var grid = self.gridElement.data("kendoGrid");
		var headerColgroup = grid.thead.parent().find("> colgroup > col");
		_.each(headerColgroup, function(col, idx) {
			if($(col).width() == 0) {
				if( _.isEmpty(autoWidthColumnIdx)) {
					autoWidthColumnIdx = idx;
				} else {
					throw Error("autoWidth는 한개 컬럼만 적용 가능합니다. 그리드 컬럼 설정을 확인해주세요.");
				}
			}
		});
		fixedWidth = _.sum(_.map(headerColgroup, function(col) {
			return $(col).outerWidth();
		}));

		if( null != grid && autoWidthColumnIdx >= 0 && fixedWidth > 20 ) {
			self.gridAuthWidthResize();
			$(window).on(resizeEventName, self.gridAuthWidthResize);
		}
	};
	self.findAuthWidthColumn();

	//컬럼 리사이징 이벤트가 발생하면 autoWidth계산 중지
	self.removeResizeEvent = function() {
		$(window).off(resizeEventName, self.gridAuthWidthResize);
	};


	//데이터 로드
	self.load = function() {
		if( self.dataSource ) {
			self.dataSource.read();
		}
	};

	self.showInitializeMessage = function() {
		self.gridElement.data("kendoGrid").dataSource.data([]);
		$(".k-grid-norecords > div").text("검색버튼을 눌러주세요").show();
	};

	//그리드 변경 (URL과 컬럼정보 다시 설정)
	self.changeGrid = function(url, columns) {
		columns = extendColumnsAlign(columns);

		self.kendoGrid.getKendoGrid().destroy();
		self.kendoGrid.empty();

		var gridOption = _.clone(self.gridOptions);
		var dataSourceOptions = _.clone(self.dataSourceOptions);
		dataSourceOptions.transport.read.url = url;
		self.dataSource = new kendo.data.DataSource(dataSourceOptions);
		gridOption.columns = columns;
		gridOption.dataSource = self.dataSource;
		self.kendoGrid = self.gridElement.kendoGrid(gridOption);

		self.findAuthWidthColumn();
	};
};

/**
 * AbleKendoTableVM
 * elementId : Grid HtmlElement ID. ex: "#grid"
 * columns: Grid columns Setting ex: [{fiedl:'mainCode', title:'메인코드'}, {fiedl:'subCode', title:'서브코드'}...]
 * [사용법]
 * new AbleKendoTableVM("#grid", self.gridColumns);
 */
function AbleKendoTableVM(elementId, columns, gridOption, eventHandler) {
	var self = this;
	self.clazz = "AbleKendoTableVM";

	//AutoWidth컬럼 용 변수
	var autoWidthColumnIdx;
	var fixedWidth;
	var minWidth = 100;
	var scrollbarWidth = 19;

    //Grid column align
    var gridColumnAlign = {
        left: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-left" } },
        center: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-center" } },
        right: { headerAttributes: { "class": "align-center valign-middle"}, attributes: { "class" : "align-right" } }
    };

    //Able Column Config
    function extendColumnsAlign(columns) {
    	return _.chain(columns).map(_.clone)
				.map(function(item) {
					if(_.has(item, 'ezAlign')) {
						_.extend(item, gridColumnAlign[item.ezAlign]);
					}
					if(_.has(item, "minWidth")) { //authWidth컬럼의 경우 minWidth값을 주면 해당값이 최소값으로 적용됨
						minWidth = item.minWidth;
					}
					return item;
				}).value();
    }
    columns = extendColumnsAlign(columns);

	//그리드 Element
	self.gridElement = $(elementId);

	//Kendo Data Source Options
	var gridOption = gridOption || {};

	//Grid 이벤트 핸들러
	self.eventHandler = eventHandler;

	var pageSize = ( false == gridOption.pageable ) ? 0 : 10;

	//Row 선택 시 선택된 Row의 Data를 저장
	self.selectedRowItem = null;

	self.dataSourceOptions = {
		data: [],
		pageSize: pageSize,
		change: function(event) {
			self.selectedRowItem = null;
	    	self.eventHandler("dataSourceChanged", null);
	    }
	};

	if ( self.eventHandler == null ){
		self.dataSourceOptions = _.omit(self.dataSourceOptions, 'change');
	}

	self.dataSource = new kendo.data.DataSource(self.dataSourceOptions);

	self.defaultGridOptions = {
		//toolbar: ["excel"],
		autoBind: false,
		dataSource: self.dataSource,
		height: 450,
		sortable: true,
		pageable: {
			refresh: false,
			previousNext: true,
			buttonCount: 10,
			pageSize: 10,
			pageSizes: appconfig.allowPageSizeList
		},
		resizable: true,
		columns: columns,
        columnResize: function(e) {
        	console.log(e.column.field, e.newWidth, e.oldWidth);
        },
        noRecords: true,
        messages: {
        	noRecords: "검색된 데이터가 없습니다."
        },
		change: function(event) {
        	self.selectedRowItem = event.sender.dataItems()[this.select().index()];
        	self.eventHandler("rowSelected", self.selectedRowItem);
        },
		selectable: "row"
	};

	if ( self.eventHandler == null ){
		self.defaultGridOptions = _.omit(self.defaultGridOptions, 'change', 'selectable');
	}

	self.gridOptions = _.defaults(gridOption, self.defaultGridOptions);

	self.setData = function(data) {
		self.dataSource.data(data);
	}

	self.kendoGrid = self.gridElement.kendoGrid(self.gridOptions);

	/**
	 *	Grid min-width Settings
	 *	그리드 컬럼 width를 설정하지 않으면 autoWidth로 처리되는데,
	 *	그리드 사이즈가 줄어들면 autoWidth 컬럼의 사이즈는 0까지 줄어들어 화면에 표시되지 않는 이슈가 있어 autoWidth컬럼의 사이즈를 동적으로 변경
	 *
	 *  fixedWidth: 컬럼 width가 정해진 컬럼들의 width 합계
	 *  autoWidthColumnIdx: 컬럼 width가 정해지지 않은 컬럼의 index
	 */
	//window Resize Event에 여러개의 핸들러를 붙이기 위해 resize이벤트에 커스텀네임을 붙여 다른 이벤트로 인식하게 만듬
	//화면에 그리드가 여러개 있는 경우...
	var resizeEventName = "resize."+elementId.substr(1);

	//컬럼 사이즈 변경
	self.gridAuthWidthResize = function() {
		var grid = self.gridElement.data("kendoGrid");
		if( !_.isEmpty(grid)) {
			var totalWidth = grid.getSize().width;
			var authWidth = totalWidth - fixedWidth - scrollbarWidth;
			//console.log("authWidth: {}, minWidth: {} ",authWidth, minWidth);
			authWidth = (authWidth < minWidth ) ? minWidth : authWidth;
			var headerCol = grid.thead.parent().find("> colgroup > col:eq("+ autoWidthColumnIdx +")");
			var bodyCol = grid.table.find("> colgroup > col:eq("+ autoWidthColumnIdx +")");

			$(headerCol).width(authWidth);
			$(bodyCol).width(authWidth);

			grid.resize();
		}
	};

	//autoWidth컬럼 검색
	self.findAuthWidthColumn = function() {
		autoWidthColumnIdx = null;

		var grid = self.gridElement.data("kendoGrid");
		var headerColgroup = grid.thead.parent().find("> colgroup > col");
		_.each(headerColgroup, function(col, idx) {
			if($(col).width() == 0) {
				if( _.isEmpty(autoWidthColumnIdx)) {
					autoWidthColumnIdx = idx;
				} else {
					throw Error("autoWidth는 한개 컬럼만 적용 가능합니다. 그리드 컬럼 설정을 확인해주세요.");
				}
			}
		});
		fixedWidth = _.sum(_.map(headerColgroup, function(col) {
			return $(col).outerWidth();
		}));

		if( null != grid && autoWidthColumnIdx >= 0 && fixedWidth > 20 ) {
			self.gridAuthWidthResize();
			$(window).on(resizeEventName, self.gridAuthWidthResize);
		}
	};
	self.findAuthWidthColumn();

	//컬럼 리사이징 이벤트가 발생하면 autoWidth계산 중지
	self.removeResizeEvent = function() {
		$(window).off(resizeEventName, self.gridAuthWidthResize);
	};

	self.query = function(){
		//console.log("=====> TABLE self.query");
		if( self.dataSource ) {
			self.dataSource.query({take: 10, skip: 0, page: 1, pageSize: 10}); //검색시 기본 1페이지로 돌아오도록 변경
		}
	}

	self.refresh = function(){
		console.log("=====> TABLE self.refresh");
		self.kendoGrid.getKendoGrid().refresh();
	}

	//그리드 변경 (URL과 컬럼정보 다시 설정)
	self.changeGrid = function(data, columns) {
		columns = extendColumnsAlign(columns);

		self.kendoGrid.getKendoGrid().destroy();
		self.kendoGrid.empty();

		var gridOption = _.clone(self.gridOptions);
		var dataSourceOptions = _.clone(self.dataSourceOptions);
		self.dataSource = new kendo.data.DataSource(dataSourceOptions);
		gridOption.columns = columns;
		gridOption.dataSource = self.dataSource;
		self.kendoGrid = self.gridElement.kendoGrid(gridOption);
		self.setData(data);
	};
};