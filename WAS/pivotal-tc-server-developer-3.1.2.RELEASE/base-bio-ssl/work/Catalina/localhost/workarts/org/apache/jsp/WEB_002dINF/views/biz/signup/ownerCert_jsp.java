/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/7.0.64.B.RELEASE
 * Generated at: 2016-02-09 07:56:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.biz.signup;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class ownerCert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/layouts/biz/include.jsp", Long.valueOf(1453120580394L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
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
      out.write('\r');
      out.write('\n');

Logger logger = LoggerFactory.getLogger(this.getClass());
boolean isDevelopment = !AbleUtil.isRealProfile();
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

if(!Boolean.TRUE.equals(request.getAttribute("pageContextJavaScriptIncluded"))) {
	request.setAttribute("pageContextJavaScriptIncluded", Boolean.TRUE);

      out.write("\t<!-- Environment - isDevelopment: ");
      out.print(isDevelopment);
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isDevelopment}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\twindow['contextPath'] = window['contextPath'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\twindow['locale'] = window['locale'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${locale}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\twindow['localeDashed'] = window['localeDashed'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${localeDashed}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\twindow['lang'] = window['lang'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lang}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\twindow['country'] = window['country'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${country}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\twindow['serverTime'] = window['serverTime'] || \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${serverTime}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</script>\r\n");
	
}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<h1 class=\"tit\">본인인증</h1>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/layouts/biz/common/breadcrumb.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"certi-wrap\">\r\n");
      out.write("\t<p class=\"txt-guide\">아래 본인인증 수단(휴대폰 또는 아이핀)을 통해 인증을 해주세요.</p>\r\n");
      out.write("\t<div class=\"certi-box-wrap ofh\">\r\n");
      out.write("\t\t<div class=\"certi-box phone\">\r\n");
      out.write("\t\t\t<h2 class=\"tit\"><span class=\"c-blue\">휴대폰 </span>인증</h2>\r\n");
      out.write("\t\t\t<p class=\"txt\">본인 명의의 휴대폰 번호로<br>본인인증이 가능합니다.</p>\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"btn-certi\" onclick=\"javascript: openKMCISWindow();\">인증하기</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"certi-box ipin\">\r\n");
      out.write("\t\t\t<h2 class=\"tit\"><span class=\"c-blue\">아이핀 </span>인증</h2>\r\n");
      out.write("\t\t\t<p class=\"txt\">본인 명의의 아이핀을 통해<br>본인인증이 가능합니다.</p>\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"btn-certi\" onclick=\"javascript: openCBAWindow();\">인증하기</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div><!-- //.certi-box-wrap -->\r\n");
      out.write("\t<div class=\"txt-certi\">\r\n");
      out.write("\t\t<h3 class=\"tit\">인증관련 문의</h3>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>- 휴대폰 인증 : 02-2033-8500 <a href=\"http://www.kmcert.com\" class=\"c-blue\" target=\"_blank\">(http://www.kmcert.com)</a></li>\r\n");
      out.write("\t\t\t<li>- 아이핀 인증 : 1577-1006 <a href=\"http://www.siren24.com\" class=\"c-blue\" target=\"_blank\">(http://www.siren24.com)</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<p class=\"txt-ps\">※서비스 이용 중 도움이 필요하시면 <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/board/faq/list\" target=\"_new\"><span class=\"c-blue\">FAQ 서비스</span></a>를 이용하세요.</p>\r\n");
      out.write("\t</div><!-- //.txt-certi -->\r\n");
      out.write("</div><!-- //.certi-wrap -->\r\n");
      out.write("\r\n");
      out.write("<form name=\"reqKMCISForm\" method=\"post\" action=\"#\">\r\n");
      out.write("    <input type=\"hidden\" name=\"tr_cert\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kmcParam.tr_cert}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("    <input type=\"hidden\" name=\"tr_url\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kmcParam.tr_url}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("</form>\r\n");
      out.write("<form name=\"reqCBAForm\" method=\"post\" action=\"#\">  \r\n");
      out.write("\t<input type=\"hidden\" name=\"reqInfo\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sirenParam.reqInfo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"retUrl\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sirenParam.retUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//KMC\r\n");
      out.write("\twindow.name = \"WorkArts_ownerCert\";\r\n");
      out.write("\tvar KMCIS_window;\r\n");
      out.write("\tfunction openKMCISWindow() {\r\n");
      out.write("\t\tvar UserAgent = navigator.userAgent;\r\n");
      out.write("\t\t/* 모바일 접근 체크*/\r\n");
      out.write("\t\tif (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null ||\r\n");
      out.write("\t\t\tUserAgent.match(/LG|SAMSUNG|Samsung/) != null) {\r\n");
      out.write("\t\t\t// 모바일일 경우 (변동사항 있을경우 추가 필요)\r\n");
      out.write("\t\t\tdocument.reqKMCISForm.target = '';\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t// 모바일이 아닐 경우\r\n");
      out.write("\t\t\tKMCIS_window = window.open(\r\n");
      out.write("\t\t\t\t\t\t\t'',\r\n");
      out.write("\t\t\t\t\t\t\t'KMCISWindow',\r\n");
      out.write("\t\t\t\t\t\t\t'width=425, height=550, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=435, top=250'\r\n");
      out.write("\t\t\t\t\t\t\t);\r\n");
      out.write("\t\t\tif (KMCIS_window == null) {\r\n");
      out.write("\t\t\t\talert(\" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \\n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \\n\\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tKMCIS_window.focus();\r\n");
      out.write("\t\t\tdocument.reqKMCISForm.target = 'KMCISWindow';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.reqKMCISForm.action = 'https://www.kmcert.com/kmcis/web/kmcisReq.jsp';\r\n");
      out.write("\t\tdocument.reqKMCISForm.submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//Siren\r\n");
      out.write("\tvar CBA_window; \r\n");
      out.write("    function openCBAWindow(){ \r\n");
      out.write("    \talert(\"서비스 준비중입니다.\");\r\n");
      out.write("    \treturn false;\r\n");
      out.write("        CBA_window = window.open('', 'IPINWindow', 'width=450, height=500, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );\r\n");
      out.write("        if(CBA_window == null){ \r\n");
      out.write("\t\t\talert(\" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \\n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \\n\\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.\");\r\n");
      out.write("        \treturn false;\r\n");
      out.write("        }\r\n");
      out.write("        CBA_window.focus();\r\n");
      out.write("\t\tdocument.reqCBAForm.action = 'https://ipin.siren24.com/i-PIN/jsp/ipin_j10.jsp';\r\n");
      out.write("        document.reqCBAForm.target = 'IPINWindow';\r\n");
      out.write("        document.reqCBAForm.submit();\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/app/biz/signup/ownerCert.js\"></script>");
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
    // /WEB-INF/layouts/biz/include.jsp(39,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty meJSON}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\twindow['me'] = window['me'] || ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${meJSON}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(";\r\n");
        out.write("\t");
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
