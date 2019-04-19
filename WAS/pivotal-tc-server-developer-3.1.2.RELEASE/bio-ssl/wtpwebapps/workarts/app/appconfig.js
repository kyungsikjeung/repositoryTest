/**
 * 어플리케이션 javascript config
 * @author Min ByeongDon <deepfree@gmail.com>
 */
var appconfig = {
	validation : {
		/*
		 * Validation 규칙에 다음과 같이 사용
		 * var validationRules = {
		 *     imageFileUploaded: eslconfig.validation.rule.imagefile,
		 *     attachedFileUploaded: eslconfig.validation.rule.attachedfile
		 * };
		 */
		rule: {
			attachedfile : { extension: "doc,docx,xls,xlsx,ppt,pptx,pdf,hwp,gul,txt,png,gif,jpg,jpeg,jpe", filesize: "5MB" },
			attachedfileForIssue : { extension: "doc,docx,xls,xlsx,ppt,pptx,pdf,hwp,gul,txt,png,gif,jpg,jpeg,jpe", filesize: "5MB" },
			imagefile : { extension: "png,gif,jpg,jpeg,jpe", filesize: "1MB" },
			patchfile : { extension: "po,jar", filesize: "50MB" }
		}
	}
}