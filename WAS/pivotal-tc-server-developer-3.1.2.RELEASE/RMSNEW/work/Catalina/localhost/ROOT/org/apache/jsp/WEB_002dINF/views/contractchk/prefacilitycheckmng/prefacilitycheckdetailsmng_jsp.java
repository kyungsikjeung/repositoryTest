/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/8.0.26.B.RELEASE
 * Generated at: 2019-04-18 05:22:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.contractchk.prefacilitycheckmng;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class prefacilitycheckdetailsmng_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(12);
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/tiles-jsp-3.0.5.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1411309930000L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.3.3.RELEASE.jar", Long.valueOf(1553470984444L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/spring-security-taglibs-4.0.3.RELEASE.jar!/META-INF/security.tld", Long.valueOf(1446175026000L));
    _jspx_dependants.put("/WEB-INF/lib/tiles-jsp-3.0.5.jar", Long.valueOf(1553470970157L));
    _jspx_dependants.put("/WEB-INF/layouts/include.jsp", Long.valueOf(1553470969818L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1553470973088L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/spring-webmvc-4.3.3.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1474264370000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-security-taglibs-4.0.3.RELEASE.jar", Long.valueOf(1553470985114L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/spring-webmvc-4.3.3.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1474264370000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("framework.util.AbleUtil");
    _jspx_imports_classes.add("org.slf4j.Logger");
    _jspx_imports_classes.add("org.slf4j.LoggerFactory");
    _jspx_imports_classes.add("org.joda.time.LocalDateTime");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write('\n');

Logger logger = LoggerFactory.getLogger(this.getClass());
boolean isDevelopment = !AbleUtil.isProductionOrStageProfile();
pageContext.setAttribute("isDevelopment", isDevelopment);

//page attribute
//${contextPath}	: application이 배포된 contextPath (정적리소스는 이 경로를 Prefix로 반드시 붙일 것)
//${locale}			: Response에서 사용할 locale (지역화 기준)
//${serverDateTime}	: 서버시간
pageContext.setAttribute("contextPath", request.getContextPath());
pageContext.setAttribute("locale", response.getLocale().toString());
pageContext.setAttribute("localeDashed", response.getLocale().toString().replace("_", "-"));
pageContext.setAttribute("lang", response.getLocale().getLanguage());
pageContext.setAttribute("country", response.getLocale().getCountry());
pageContext.setAttribute("serverTime", LocalDateTime.now());

if(!Boolean.TRUE.equals(pageContext.getAttribute("pageContextJavaScriptIncluded"))) {
	pageContext.setAttribute("pageContextJavaScriptIncluded", Boolean.TRUE);

      out.write("\t<!-- Environment - isDevelopment: ");
      out.print(isDevelopment);
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isDevelopment}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" -->\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\twindow['contextPath'] = window['contextPath'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("\t\twindow['locale'] = window['locale'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${locale}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("\t\twindow['localeDashed'] = window['localeDashed'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${localeDashed}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("\t\twindow['lang'] = window['lang'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lang}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("\t\twindow['country'] = window['country'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${country}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</script>\n");

}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"table-responsive\" style=\"font-size:20px;\">\r\n");
      out.write("\t<table class=\"table table-bordered\" data-bind=\"with:summaryVM\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr class=\"bg-blue\">\r\n");
      out.write("\t\t\t\t<th class=\"text-center\" style=\"background-color: #6d57c7;\">협력업체명</th>\r\n");
      out.write("\t\t\t\t<th class=\"text-center\">담당자</th>\r\n");
      out.write("\t\t\t\t<th class=\"text-center\">연락처</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody>\r\n");
      out.write("\t\t\t<tr class=\"text-center\" data-bind=\"with:data\">\r\n");
      out.write("\t\t\t\t<td data-bind=\"text:subcontractName\"></td>\r\n");
      out.write("\t\t\t\t<td data-bind=\"text:chargerName\"></td>\r\n");
      out.write("\t\t\t\t<td data-bind=\"text:phoneNo\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"text-right\" style=\"padding-top: 20px;\">\r\n");
      out.write("\t<button type=\"button\" data-bind=\"click:moveParentPage\" class=\"btn btn-primary btn-xs\" style=\"font-size:16px;\"><b><i class=\"icon-plus3\"></i></b> 협력업체 목록 보기</button>\r\n");
      out.write("</div>\r\n");
      out.write("<br/>\r\n");
      out.write("\r\n");
      out.write("<form id=\"detailsForm\" class=\"form-horizontal\" onsubmit=\"return false;\" action=\"#\" data-bind=\"with:detailsVM\" style=\"padding-top: 40px;\">\r\n");
      out.write("\t<div class=\"panel panel-flat\">\r\n");
      out.write("\t\t<div class=\"panel-body\" style=\"padding-top: 10px\">\r\n");
      out.write("\t\t\t<div class=\"text-left\" style=\"padding-top: 10px; float:left; font-size:20px; color:red;\">\r\n");
      out.write("\t\t\t\t<label >※ 전체저장 클릭시 점검완료 및 점수가 저장됩니다.</label>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"text-right\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" data-bind=\"click: updateFile.bind(null, 'ALL')\" style=\"font-size:20px;\"><b><i class=\"icon-checkmark3\"></i></b> 전체저장</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"row\" style=\"font-size: 18px; padding-top: 10px\">\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t\t<!-- ko foreach: data -->\r\n");
      out.write("\t\t\t\t<div class=\"panel-group panel-group-control panel-group-control-right\" id=\"accordion-control-issue\">\r\n");
      out.write("\t\t\t\t\t<div class=\"panel panel-white\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"panel-heading\" style=\"background-color: #26a69a;margin-top: 0px;margin-bottom: 0px;padding-top: 10px;padding-bottom: 10px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<h6 class=\"panel-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a data-toggle=\"collapse\" data-bind=\"attr: {href:'#collapsible-'+ displayOrder}\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- <a data-toggle=\"collapse\" data-parent=\"#accordion-control-issue\" data-bind=\"attr: {href:'#collapsible-'\"+ displayOrder +\"}\" > -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"icon-cog3 position-left\" style=\"margin-top: -5px;\"></i><span style=\"font-size: 20px;color:#fff;\" data-bind=\"text:displayQuestionName\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div data-bind=\"attr: {id:'collapsible-'+ displayOrder}\" class=\"panel-collapse collapse in\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-md-2 control-label text-left\" for=\"chkStateYn\">적정여부</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-10\" style=\"padding-top:7px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label for=\"보유\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" style=\"margin-right:5px;\" data-bind=\"value: 'Y', checked: chkStateYn, attr: {id:'chkStateYn_'+displayOrder+'_Y', name:'chkStateYn_'+displayOrder}\"/><span style=\"padding-right:40px;\" data-bind=\"text:pageParam.CHK_STATE_YN_CODE_MAP['Y']\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label for=\"미보유\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" style=\"margin-right:5px;\" data-bind=\"value: 'N', checked: chkStateYn, attr: {id:'chkStateYn_'+displayOrder+'_N', name:'chkStateYn_'+displayOrder}\"/><span style=\"padding-right:40px;\" data-bind=\"text:pageParam.CHK_STATE_YN_CODE_MAP['N']\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label for=\"해당없음\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"radio\" style=\"margin-right:5px;\" data-bind=\"value: 'X', checked: chkStateYn, attr: {id:'chkStateYn_'+displayOrder+'_X', name:'chkStateYn_'+displayOrder}\"/><span data-bind=\"text:pageParam.CHK_STATE_YN_CODE_MAP['X']\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-md-2 control-label text-left\" for=\"description\">설명</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<textarea rows=\"5\" width=\"80%\" name=\"description\" style=\"width:95%\" data-bind=\"value:description\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- ko if: addItemCode == 'SENIOR' -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-md-2 control-label text-left\" for=\"chkStateYn\">보유여부</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!-- ko foreach: ADD_ITEM_SENIOR_TYPE_LIST -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label  class=\"col-md-2 control-label text-left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" data-bind=\"checked: $parent[mapKey]\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<span data-bind=\"text:mapValue\" style=\"padding-right:20px;\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!-- /ko -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- /ko -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"form-group marginbottom10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label for=\"roleName\" class=\"col-md-2 control-label text-left\">사진첨부</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-10\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<table class=\"q-table\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"input-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div><label class=\"plupload_button plupload_add\" data-bind=\"attr:{id:'btnLocalFileInputNo'+displayOrder+'_'}\" for=\"attached-file\" >사진추가</label></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div data-bind=\"attr:{id:'areaFileInputNo'+displayOrder+'_'}\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"text-info\">※ ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ALLOWED_IMAGE_FILE_EXTENSION_FOR_ATTACED}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" 파일만 가능합니다.</span><br/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"text-info\" data-bind=\"text:'※ 첨부파일은 최대 '+$root.detailsVM.fileMaxCnt +' 개 까지 업로드 가능합니다.'\" ></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div data-bind=\"attr:{id:'areaFilePreviewNo'+displayOrder+'_'}\" class=\"gray-box\" style=\"min-height: 60px;\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- ko if: chkStateYn() == 'N' -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-md-2 control-label text-left\" for=\"resolvedYn\">개선여부</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-md-10\" style=\"padding-top:7px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"padding-right:20px;\">개선완료</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" data-bind=\"checked: resolvedYn\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- /ko -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"text-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" data-bind=\"click: $parent.updateFile.bind(null, displayOrder)\" style=\"font-size:20px;\"><b><i class=\"icon-checkmark3\"></i></b><span data-bind=\"text:' '+questionName+' 저장'\"> </span></button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /ko -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- <div class=\"text-right\" style=\"padding-top: 20px;\"> -->\r\n");
      out.write("\t\t\t<div class=\"text-left\" style=\"padding-top: 10px; float:left; font-size:20px; color:red;\">\r\n");
      out.write("\t\t\t\t<label >※ 전체저장 클릭시 점검완료 및 점수가 저장됩니다.</label>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"text-right\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-primary btn-xs\" data-bind=\"click: updateFile.bind(null, 'ALL')\" style=\"font-size:20px;\"><b><i class=\"icon-checkmark3\"></i></b> 전체저장</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/app/contractchk/prefacilitycheckmng/prefacilitycheckdetailsmng.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  \tvar pageParam = {\r\n");
      out.write(" \t\t\tCHK_STATE_YN_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${CHK_STATE_YN_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write(" \t\t\tADD_ITEM_SENIOR_TYPE_LIST: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ADD_ITEM_SENIOR_TYPE_LIST}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write(" \t\t\tADD_ITEM_SENIOR_TYPE_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ADD_ITEM_SENIOR_TYPE_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write(" \t\t\tCONTRACT_SEQ: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${CONTRACT_SEQ}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("',\r\n");
      out.write(" \t\t\tSUBCONTRACT_SEQ: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${SUBCONTRACT_SEQ}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("',\r\n");
      out.write(" \t\t\tFILE_ROOT_PATH: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${FILE_ROOT_PATH}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("',\r\n");
      out.write(" \t\t\tALLOWED_IMAGE_FILE_EXTENSION_FOR_ATTACED: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ALLOWED_IMAGE_FILE_EXTENSION_FOR_ATTACED}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("'\r\n");
      out.write("\t};\r\n");
      out.write("\r\n");
      out.write("  \tsetPageLocation(\"contractchk/prefacilitycheckmng\");\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/layouts/include.jsp(38,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty meJSON}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\twindow['me'] = window['me'] || ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${meJSON}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
        out.write(';');
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
