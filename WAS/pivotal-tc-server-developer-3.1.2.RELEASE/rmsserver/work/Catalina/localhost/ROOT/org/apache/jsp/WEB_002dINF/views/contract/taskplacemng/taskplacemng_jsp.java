/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/8.0.26.B.RELEASE
 * Generated at: 2019-03-08 05:50:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.contract.taskplacemng;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class taskplacemng_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(12);
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/tiles-jsp-3.0.5.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1411309930000L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.3.3.RELEASE.jar", Long.valueOf(1551961329392L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/spring-security-taglibs-4.0.3.RELEASE.jar!/META-INF/security.tld", Long.valueOf(1446175026000L));
    _jspx_dependants.put("/WEB-INF/lib/tiles-jsp-3.0.5.jar", Long.valueOf(1551961313267L));
    _jspx_dependants.put("/WEB-INF/layouts/include.jsp", Long.valueOf(1551961312806L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1551961316667L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/Java/workspace/rms_check_platform/RMS_Check_Platform/WebContent/WEB-INF/lib/spring-webmvc-4.3.3.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1474264370000L));
    _jspx_dependants.put("/WEB-INF/lib/spring-security-taglibs-4.0.3.RELEASE.jar", Long.valueOf(1551961330131L));
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
      out.write("<div class=\"panel panel-flat\" data-bind=\"with: searchData, enterKey: $root.search\">\r\n");
      out.write("\t<div class=\"panel-heading\">\r\n");
      out.write("\t\t<span class=\"text-semibold\">검색조건</span>\r\n");
      out.write("\t\t<div class=\"heading-elements\">\r\n");
      out.write("\t\t\t<ul class=\"icons-list\">\r\n");
      out.write("\t\t\t\t<li><a data-action=\"collapse\"></a></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<a class=\"heading-elements-toggle\"><i class=\"icon-menu\"></i></a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"panel-body paddingbottom0\">\r\n");
      out.write("\t\t<form class=\"form-horizontal\" role=\"form\">\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">원청업체명</label>\r\n");
      out.write("\t\t\t\t\t<select id=\"searchContractSeq\" class=\"form-control input-xs\" name=\"searchContractSeq\" data-bind=\"options: $root.contractSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '전체', value: searchContractSeq\"></select>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">공장명</label>\r\n");
      out.write("\t\t\t\t\t<select id=\"searchFactorySeq\" class=\"form-control input-xs\" name=\"searchFactorySeq\" data-bind=\"options: $root.factorySeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '전체', value: searchFactorySeq\"></select>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">부서명</label>\r\n");
      out.write("\t\t\t\t\t<select id=\"searchDepartmentSeq\" class=\"form-control input-xs\" name=\"searchDepartmentSeq\" data-bind=\"options: $root.departmentSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '전체', value: searchDepartmentSeq\"></select>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">공정명</label>\r\n");
      out.write("\t\t\t\t\t<select id=\"searchProcessSeq\" class=\"form-control input-xs\" name=\"searchProcessSeq\" data-bind=\"options: $root.processSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '전체', value: searchProcessSeq\"></select>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">공종(작업장소)명</label>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" placeholder=\"공종(작업장소)명\" data-bind=\"value: searchTaskplaceName\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label text-right\">사용여부</label>\r\n");
      out.write("\t\t\t\t\t<select class=\"form-control input-xs\" data-bind=\"options: $root.useYnSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '전체', value: searchUseYn\"></select>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t<div class=\"text-right\">\r\n");
      out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-bind=\"click: $root.search\">검색<i class=\"glyphicon glyphicon-search position-right\"></i></button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"grid\"></div>\r\n");
      out.write("<div class=\"text-left\" style=\"padding-top: 10px; float: left;\">\r\n");
      out.write("\t<a href=\"#\" class=\"btn bg-teal\" data-bind=\"click: excelDownload\">Excel Download<i class=\"icon-file-excel position-right\"></i></a>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"text-right\" style=\"padding-top: 10px;\">\r\n");
      out.write("\t<button type=\"button\" data-bind=\"click: onCreateClick\" class=\"btn btn-primary btn-xs\"><b><i class=\"icon-plus3\"></i></b> 공종 등록</button>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<br/>\r\n");
      out.write("<form id=\"detailsForm\" class=\"form-horizontal\" action=\"#\" data-bind=\"visible: detailsVM.isVisible\">\r\n");
      out.write("\t<div class=\"panel panel-flat\">\r\n");
      out.write("\t\t<div class=\"panel-heading\">\r\n");
      out.write("\t\t\t<h5 class=\"panel-title\"><i class=\"icon-checkmark3 position-left\"></i> 세부정보</h5>\r\n");
      out.write("\t\t\t<div class=\"heading-elements\">\r\n");
      out.write("\t\t\t\t<ul class=\"icons-list\">\r\n");
      out.write("\t\t\t\t\t<li><a data-action=\"collapse\"></a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<a class=\"heading-elements-toggle\"><i class=\"icon-menu\"></i></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"panel-body\" data-bind=\"with: detailsVM\">\r\n");
      out.write("\t\t\t<div class=\"row\" data-bind=\"with: data\">\r\n");
      out.write("\t\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t\t<legend class=\"text-semibold\" style=\"height:30px;\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"col-md-8\"><i class=\"icon-lock5 position-left\"></i> 공종(작업장소) 관리 상세정보</span>\r\n");
      out.write("\t\t\t\t\t</legend>\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"contractSeq\">원청업체명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"contractSeq\" class=\"form-control input-xs\" name=\"contractSeq\" data-bind=\"options: $root.contractSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '선택', value: contractSeq, enable:$parent.isCreateMode\">\r\n");
      out.write("\t\t\t\t                </select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"factorySeq\">공장명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"factorySeq\" class=\"form-control input-xs\" name=\"factorySeq\" data-bind=\"options: $root.factorySeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '선택', value: factorySeq, enable:$parent.isCreateMode\">\r\n");
      out.write("\t\t\t\t                </select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"departmentSeq\">부서(팀)명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"departmentSeq\" class=\"form-control input-xs\" name=\"departmentSeq\" data-bind=\"options: $root.departmentSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '선택', value: departmentSeq, enable:$parent.isCreateMode\">\r\n");
      out.write("\t\t\t\t                </select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"processSeq\">공정명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"processSeq\" class=\"form-control input-xs\" name=\"processSeq\" data-bind=\"options: $root.processSeqSelectOptions, optionsText:'text', optionsValue:'value', optionsCaption: '선택', value: processSeq, enable:$parent.isCreateMode\">\r\n");
      out.write("\t\t\t\t                </select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"taskplaceName\">공종(작업장소)명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"taskplaceName\" data-bind=\"value: taskplaceName\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"chargerName\">담당자명<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"chargerName\" data-bind=\"value: chargerName\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"phoneNo\">담당자 일반전화</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"phoneNo\" data-bind=\"value: phoneNo\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"mobilePhoneNo\">담당자 휴대폰번호<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"mobilePhoneNo\" data-bind=\"value: mobilePhoneNo\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"mainPhoneNo\">대표 전화번호</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"mainPhoneNo\" data-bind=\"value: mainPhoneNo\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"emergencyPhoneNo\">비상연락망</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"emergencyPhoneNo\" data-bind=\"value: emergencyPhoneNo\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"businessNo\">사용여부<span class=\"text-danger\">*</span></label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"form-control input-xs\" name=\"useYn\" data-bind=\"options: $root.useYnSelectOptions, optionsText:'text', optionsValue:'value', value: useYn\"></select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"memo\" >메모</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<textarea id=\"memo\" class=\"form-control input-xs\" name=\"memo\" data-bind=\"value: memo\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!--  ko if: $parent.isEditMode -->\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"managerId\">등록자ID</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"managerId\" data-bind=\"value: managerId\" disabled=\"disabled\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"regDate\">등록일</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"regDate\" data-bind=\"momentISO: regDate\" disabled=\"disabled\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"updManageId\">수정자ID</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"updManageId\" data-bind=\"value: updManageId\" disabled=\"disabled\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"col-md-4 control-label text-right\" for=\"updDate\">최종수정일</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control input-xs\" name=\"updDate\" data-bind=\"momentISO: updDate\" disabled=\"disabled\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- /ko -->\r\n");
      out.write("\t\t\t\t</fieldset>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"text-right\" data-bind=\"visible: isCreateMode\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" data-bind=\"click: cancel\" class=\"btn btn-xs\"><b><i class=\"icon-cross\"></i></b> 취소</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" data-bind=\"click: insertData\" class=\"btn btn-primary btn-xs\"><b><i class=\"icon-checkmark3\"></i></b> 추가</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"text-right paddingbottom20\" data-bind=\"visible: isEditMode\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" data-bind=\"click: cancel\" class=\"btn btn-xs\"><b><i class=\"icon-cross\"></i></b> 취소</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" data-bind=\"click: deleteData\" class=\"btn btn-warning btn-xs\"><b><i class=\"icon-minus3\"></i></b> 삭제</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" data-bind=\"click: updateData\" class=\"btn btn-primary btn-xs\"><b><i class=\"icon-checkmark3\"></i></b> 수정</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"col-md-12\" data-bind=\"visible: isEditMode\">\r\n");
      out.write("\t\t\t\t<div class=\"panel-group panel-group-control panel-group-control-right\" id=\"accordion-control-subcontractListGrid\">\r\n");
      out.write("\t\t\t\t\t<div class=\"panel panel-white\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"panel-heading\">\r\n");
      out.write("\t\t\t\t\t\t\t<h6 class=\"panel-title\"> 협력업체 목록\r\n");
      out.write("\t\t\t\t\t\t\t\t<a data-toggle=\"collapse\" data-parent=\"#accordion-control-subcontractListGrid\" href=\"#collapsible-subcontractListGrid\" aria-expanded=\"true\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"collapsible-subcontractListGrid\" class=\"panel-collapse collapse in\" aria-expanded=\"true\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=subcontractListGrid></div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tsetPageLocation(\"contract/taskplacemng\");\r\n");
      out.write("\tvar pageParam = {\r\n");
      out.write("\t\tUSE_YN: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${USE_YN}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("\t\tCONTRACT_LIST_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${CONTRACT_LIST_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("\t\tFACTORY_LIST_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${FACTORY_LIST_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("\t\tDEPARTMENT_LIST_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${DEPARTMENT_LIST_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("\t\tPROCESS_LIST_CODE_MAP: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${PROCESS_LIST_CODE_MAP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t};\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/app/contract/taskplacemng/taskplacemng.js\"></script>");
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
