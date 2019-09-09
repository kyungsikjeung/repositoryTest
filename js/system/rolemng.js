var roleMng = null;

function RoleMngVM(){
    var self = this;
    self.clazz = "RoleMngVM";
    self.formId = "RoleMng"
    vmExtender.extendGridVM(self);
    self.listURL = contextPath + "/system/rolemng/api/page.json";
    self.detailsURL = contextPath + "/system/rolemng/api/details";


	//Search VM
	self.searchData = ko.observable({
		searchAuthorName: "",
		searchAuthorCode: "",
		searchUseYn: ""
    });
    
    self.searchCondition = {};

    self.applySearchCondition = function(param) {
		return _.extend(param, self.searchCondition);
    }
    
    self.search = function() {
		// self.searchCondition = _.chain(self.searchData())
		// 	.omit(_.isEmpty)
		// 	.mapObject(function(val, key) { return _.isString(val)? encodeURIComponent(val) : val; })
        //     .value();    
        //self.grid.load();    
    };

    /** test 용도 */
    $("#grid").kendoGrid({
        columns: self.gridColumns,
        dataSource: [
            { no: 1, authorCode: "2222",authorName:"joe",rgsde:"2019-05-01" },
            { no: 2, authorCode: "333",authorName:"jo3",rgsde:"2019-05-01" },
            { no: 3, authorCode: "333",authorName:"jo3",rgsde:"2019-05-01" },
            { no: 4, authorCode: "333",authorName:"jo3",rgsde:"2019-05-01" }
        ]
      });
    
    	//Create
	self.onCreateClick = function() {
		self.detailsVM.startCreate({
			authorCode: null,
			authorName: null,
			rgsde: "",
		});
    };

    $("#grid thead [data-field=authorCode] .k-link").html("권한")
    $("#grid thead [data-field=CustomerNumber] .k-link").html("NewTitle")
    
    self.onGridEvent = function(type, data) {
		switch(type) {
			case "rowSelected" :
                    self.detailsVM.startEdit(
                        { no: 1, authorCode: "2222",authorName:"joe",rgsde:"2019-05-01" },   
                    );
				// $.ajax({
				// 	url: $.format("{0}/{1}.json", self.detailsURL, data.contractChecklistSeq),
				// 	type: "GET",
				// 	contentType: "application/json",
				// 	success: function(data) {
				// 		if(ezUtil.checkRESTResponseMsg(data)) {
				// 			self.detailsVM.startEdit(data.body);
				// 		}
				// 	}
				// });
				break;
			case "dataSourceChanged":
				self.detailsVM.cancel();
				break;
		}
    };
    


	self.gridColumns = [
		{ field: "no", title: "No", width: 40, ezAlign: 'center'},
		{ field: "authorCode", title: "권한코드", width: 40, ezAlign: 'center'},
		{ field: "authorName", title: "권한명", width: 40, ezAlign: 'center'},
		{ field: "rgsde", title: "등록일", width: 40, ezAlign: 'center'},
    ];
    
    self.grid = new AbleKendoGridVM("#grid", self.listURL, self.gridColumns, self.onGridEvent, self.applySearchCondition);

    self.detailsVM = new DetailsVM(self.detailsURL, self.grid.load);

    self.init = function(){
        //TODO :: serach 추가 해주기
    }
}


function DetailsVM(detailsURL, onDetailsChanged) {
    var self = this;
    self.clazz = "DetailsVM";
    self.detailsURL = detailsURL;
	self.onDetailsChanged = onDetailsChanged || function() {};
	vmExtender.extendValidatableVM(self);
	self.formId = "#detailsForm";
	self.validationRules = {
        common: {
            //  : {required: true},
            // useYn: { required: true }
        }
    };
    self.validationRules.insert = _.extend(_.clone(self.validationRules.common), {
    }); 
    self.validationRules.update = _.extend(_.clone(self.validationRules.common), {
    });  
    self.originalData = ko.observable(null);
	self.data = ko.observable(null);
	var setData = function(data) {
		var dataCloned = _.clone(data);
        var originalData = JSON.parse(ko.toJSON(dataCloned));
		self.originalData(originalData);
        self.data(dataCloned);

        if(self.isEditMode() || self.isCreateMode()){
            alert("권한그룹 가지고 오기");
            // 권한 그룹을 가지고 옴
            self.getRoleGroup(data.authorCode); 
        }
	};
    var pkColumn = "authorCode";
    self.isEditMode = ko.computed(function() { return !_.isNull(self.originalData()) && !_.isBlank(self.originalData()[pkColumn]); });
	self.isCreateMode = ko.computed(function() { return !_.isNull(self.originalData()) && _.isBlank(self.originalData()[pkColumn]); });
	self.isVisible = ko.computed(function() { return self.isEditMode() || self.isCreateMode() });

    self.startEdit = function(details) {
		console.log(self.clazz + ".startEdit => ", details);
		setData(details);
		$(window).scrollTo(self.formId, 300);
    };
    

    // 권한의 권한그룹 목록
    self.selectedRowRoleGroupList = null;
    self.relationshipMap = null;

    // 연결된 권한그룹 목록 가져오기 , 선택된 권한의 권한 그룹 목록 체크 여부 가져오기
    // pk = AuthorCode
    self.getRoleGroup = function(pk){
        // 1. 서버 버젼
        // 2. 로컬버젼


        // 전체 권한 리스트 
        self.totalRoleGroupList=
        [ 
            {authorgroupCode: "111", authorgroupName : "HQADMIN-재난본부 관리자" },
            {authorgroupCode: "222", authorgroupName : "HQADMIN-재난본부 관리자" },
            {authorgroupCode: "333", authorgroupName : "HQADMIN-재난본부 관리자" }
        ];

        // 권한 그룹 소유 여부()
        // pk = AuthorCode
        if(pk != null){
            alert("기존권한 그룹 체크 상태확인(수정모드)");
            // TODO : 권한의 권한 그룹 상태 ajax 로 pk 값으로 권한 그룹 체크 상태 가지고 오기
            var checkedRoleGroupList = [{authorgroupCode: "111"},{authorgroupCode: "111"}]
            // 
            
            
        }else{
            alert("기존 권한 그룹 여부 x(생성 모드)")
        }

 

        //권한그룹 소유 권한정보에서 가져온.. 선택된 권한이 어떤 권한 그룹에 속해있는지.
        // pk 값이 없는 startCreate 부분에도 사용.. 
        self.relationshipMap = [
            {authorgroupCode: "111", authorCode : "111"},
            {authorgroupCode: "222", authorCode : "111"},
        ]
    }

	self.startCreate = function(details) {
		console.log(self.clazz + ".startCreate => ", details);
		setData(details);
		$(window).scrollTo(self.formId, 300);
	};
	//취소
	self.cancel = function() {
		setData(null);
	};

	//삭제
	self.deleteData = function() {
		console.log(self.clazz + ".deleteData => ", self.originalData());
		if(confirm('삭제하시면 연결된 원청업체별 점검항목까지 삭제 됩니다.\n삭제하시겠습니까?')) {
			var originalData = self.originalData();
			$.ajax({
				url: $.format(self.detailsURL+"/{0}/{1}/{2}", "delete", originalData.contractChecklistSeq,originalData.contractSeq),
				type: "POST",
				contentType: "application/json",
				data: null,
				success: function(data) {
					if(ezUtil.checkRESTResponseMsg(data)) {
						_.defer(function() {
							setData(null);
							self.onDetailsChanged();
						})
					}
				}
			});
		}
	};

	//등록
	self.insertData = function() {
		console.log(self.clazz + ".insertData => ", self.data());
		var rule = self.validationRules.insert;
		if( self.isCreateMode() && self.validate(rule, self.formId) ) {
			var dataCloned = _.omit(_.clone(self.data()), 'regDate', 'updDate');
			$.ajax({
				url: $.format(self.detailsURL+"/{0}", "insert"),
				type: "POST",
				contentType: "application/json",
				data: dataCloned,
				success: function(data) {
					if(ezUtil.checkRESTResponseMsg(data)) {
						setData(null);
						self.onDetailsChanged();
					}
				}
			});
		}
	};

	//수정
	self.updateData = function() {
		console.log(self.clazz + ".updateData => ", self.data());
		var rule = self.validationRules.update;
		if( self.isEditMode() && self.validate(rule, self.formId)) {
			var originalData = self.originalData();
			var dataCloned = _.omit(_.clone(self.data()), 'regDate', 'updDate');
			$.ajax({
				url: $.format(self.detailsURL+"/{0}/{1}", "update", originalData.contractChecklistSeq),
				type: "POST",
				contentType: "application/json",
				data: dataCloned,
				success: function(data) {
					if(ezUtil.checkRESTResponseMsg(data)) {
						setData(null);
						self.onDetailsChanged();
					}
				}
			});
		}
	};

	//init
	self.init = function() {
		console.log(self.clazz + " Initialized.");
	};

}

$(document).ready(function () {
    ezUtil.ezInitialize(false/*blockUsingPageAjaxIndicator*/);
	rootVM = new RoleMngVM();
	rootVM.init();
	ko.applyBindings(rootVM);
});
