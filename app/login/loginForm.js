/**
 *
 */
var rootVM = null;

function LoginViewModel() {

	var self = this;
	self.form = $("#loginForm");

	self.userName = ko.observable();
	self.userNameFocus = ko.observable(true);

	self.userPass= ko.observable();
	self.userPassFocus = ko.observable(false);

	self.doLogin = function() {
		if ( _.isEmpty(self.userName()) ){
			alert("아이디를 입력해주세요.");
			self.userNameFocus(true);
			return false;
		}
		if ( _.isEmpty(self.userPass()) ){
			alert("패스워드를 입력해주세요.");
			self.userPassFocus(true);
			return false;
		}
		self.form.submit();
	};

	self.init = function() {
		console.log("knockout binding");
	};
}

$(document).ready(function() {
	rootVM = new LoginViewModel();
	rootVM.init();

	ko.applyBindings(rootVM);

	if ( !_.isEmpty(errorMsg) ){
		alert(errorMsg);
	}
});
