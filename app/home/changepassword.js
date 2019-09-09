/**
 *
 */
var rootVM = null;

function HomeViewModel() {

	var self = this;
	self.form = $("#userPasswordForm");

	self.originalPassword = ko.observable();
	self.newPassword = ko.observable();
	self.newPasswordRe = ko.observable();
	self.wrongPwdReason = ko.observable();

	self.onPwdChange = function(){
		if ( self.newPassword().length < 4 ){
			self.wrongPwdReason('4자리 이상 입력해주세요.');
		} else if ( !(/^[^\\\/\:\<\>\*\?\| ]*$/.test(self.newPassword())) ){
			self.wrongPwdReason('\\/:*?\"<>| 및 공백을 포함할 수 없습니다.');
		} else if ( !s.isBlank(self.newPasswordRe()) && self.newPassword() != self.newPasswordRe() ){
			self.wrongPwdReason('비밀번호가 일치하지 않습니다. 다시 시도해주세요.');
		} else {
			self.wrongPwdReason('');
		}
	}

	self.doChangePassword = function() {
		if ( s.isBlank(self.originalPassword()) ){
			alert("현재 비밀번호를 입력해주세요.");
			$('#originalPassword').focus();
			return false;
		}
		if ( s.isBlank(self.newPassword()) ){
			alert("변경 비밀번호를 입력해주세요.");
			$('#newPassword').focus();
			return false;
		}
		if ( s.isBlank(self.newPasswordRe()) ){
			alert("비밀번호 확인을 입력해주세요.");
			$('#newPasswordRe').focus();
			return false;
		}
		if ( !s.isBlank(self.wrongPwdReason()) ){
			alert(self.wrongPwdReason());
			$('#wrongPwdReason').focus();
			return false;
		}
		var dataCloned = ko.toJS(_.pick(rootVM, ['originalPassword','newPassword']));
		$.ajax({
			url: $.format(contextPath+"/{0}", "home/api/changepassword"),
			type: "POST",
			contentType: "application/json",
			data: dataCloned,
			success: function(data) {
				if(ezUtil.checkRESTResponseMsg(data)) {
					alert('비밀번호가 변경되었습니다.\n 다시 로그인 해주세요.');
					opener.location.href=contextPath+"/logout";
					window.close();
				}
			}
		});
	};

	self.init = function() {
		console.log("knockout binding");
	};
}

$(document).ready(function() {
	//공통 초기화
	ezUtil.ezInitialize(false/*blockUsingPageAjaxIndicator*/);

	rootVM = new HomeViewModel();
	rootVM.init();

	ko.applyBindings(rootVM);
});
