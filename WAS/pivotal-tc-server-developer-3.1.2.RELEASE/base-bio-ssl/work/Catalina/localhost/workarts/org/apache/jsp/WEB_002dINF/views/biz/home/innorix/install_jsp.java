/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/7.0.64.B.RELEASE
 * Generated at: 2016-06-06 10:16:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.biz.home.innorix;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class install_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>Innorix File Transfer Solution - Install</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/innorix/common/innorix.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("body { font-family: 'Dotum', '돋음', Arial sans-serif; font-size:12px; }\r\n");
      out.write("a { margin:0; padding:0; font-size:100%; vertical-align:baseline; background:transparent; color:#777776; text-decoration:none; }\r\n");
      out.write("#instalWrap { position:absolute; left:50%; top:20%; margin-left:-248px; padding:5px 5px 5px 5px; background:#a7bdd7; width:497px;  font-size:14px; overflow:hidden }\r\n");
      out.write("table.table_style_install { padding:10px; margin:10px;  width:477px; border:1px solid #317de5; background:#f7f7f7; color:#666666; }\r\n");
      out.write("table.table_style_install tr td { height:70px; line-height:19px; border-bottom:0; }\r\n");
      out.write("table.table_style_install tr td h2 { color:#333333; font-weight:bold; padding-top:5px; }\r\n");
      out.write("table.table_style_install span.installBox { width:195px; height:35px; display:block; text-align:left; line-height:14px;  background:#2e8be9; border:1px solid #317de5; margin:0; font-size:14px; padding:5px 0 0 10px; color:#ffffff; cursor:pointer; }\r\n");
      out.write("table.table_style_install span.installBoxOver { background:#317de5; cursor:pointer; }\r\n");
      out.write("table.table_style_install2 span.installBox { width:195px; height:35px; display:block; text-align:left; line-height:14px;  background:#2e8be9; margin:0; font-size:14px; padding:5px 0 0 10px; color:#ffffff; cursor:pointer; }\r\n");
      out.write("table tr#extra_tr { display: none}\r\n");
      out.write("table tr#extra_tr.on { display: table-row}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"instalWrap\">\r\n");
      out.write("\t<div id=\"install_msg\" style=\"padding:0; background:#ffffff; width:495px;  font-size:14px; display:block; border:1px solid #d7d7d7;\">\r\n");
      out.write("\t\t<div id=\"install_msg_txt\">\r\n");
      out.write("\t\t<table class=\"table_style_install\" style=\"padding:10px; margin:0px;  width:100%; border:0px solid #317de5; background:#f7f7f7; color:#666666;\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"center\" style=\"padding-top:35px;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/innorix/common/image/install_icon.png\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td style=\"text-align:center;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/innorix/common/image/install_text.png\" alt=\"install\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr id=\"extra_tr\">\r\n");
      out.write("\t\t\t\t<td align=\"center\" style=\"padding-top:10px;\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<center>\r\n");
      out.write("\t\t\t<table class=\"table_style_install2\" style=\"padding:10px; margin:0; width:100%; border:0px solid #317de5; background:#f7f7f7; color:#666666;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\" style=\"padding-top:10px;\">\r\n");
      out.write("\t\t\t\t\t\t<a id=\"innomp_download_link\" href=\"../package/InnoGMP_Win.exe\" >\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"installBox\" style=\"padding-top:10px;\" id=\"innomp_download_button\"><b>설치파일 다운로드</b><br/><span style=\"font-size:12px; padding-top:3px;\" id=\"innomp_download_str\">Version 7.2 for Windows</span></span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t<a id=\"innomp_download_link2\" href=\"../package/InnoGMP_Win.exe\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"installBox\" style=\"padding-top:10px;\" id=\"innomp_download_button2\"><b>설치파일 다운로드</b><br/><span style=\"font-size:12px; padding-top:3px;\" id=\"innomp_download_str2\">Version 7.2 for Windows</span></span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr style=\"height:15px;\">\r\n");
      out.write("\t\t\t\t\t<td style=\"height:15px;\"></td>\r\n");
      out.write("\t\t\t\t\t<td style=\"height:15px;\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</center>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"innomp_check_obj\"></div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tInstall.Init();\r\n");
      out.write("\tInstall.SelectPackage();\r\n");
      out.write("\tInstall.UpdateStatus();\r\n");
      out.write("\r\n");
      out.write("\tbrowserDetect.init();\r\n");
      out.write("\t\r\n");
      out.write("    var auto_down_flag = true;\r\n");
      out.write("\tvar innomp_detected_os = browserDetect.OS;\r\n");
      out.write("\tvar innomp_download_file = \"\", innomp_download_file2 = \"\";\r\n");
      out.write("\r\n");
      out.write("\tif (innomp_detected_os == \"Win32\") {\r\n");
      out.write("\t\tinnomp_download_file = \"InnoGMP_Win.exe\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for Windows\";\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (innomp_detected_os == \"Win64\") {\r\n");
      out.write("\t\tinnomp_download_file = \"InnoGMP_Win.exe\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for Windows\";\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (innomp_detected_os == \"Linux i686\") {\r\n");
      out.write("\t\tinnomp_download_file = \"innogmp7-7.2.0.582-x86.rpm\";\r\n");
      out.write("\t\tinnomp_download_file2 = \"innogmp7-7.2.0.582-x86.deb\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for Linux(.RPM)\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str2\").innerHTML = \"Version 7.2 for Linux(.DEB)\";\r\n");
      out.write("\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').style.cssText = \"float:left; margin-left:30px\";\r\n");
      out.write("\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link2').href = Install.InnoPackage + \"/\" + innomp_download_file2;\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link2').style.cssText = \"display:inline; float:left; margin-left:10px\";\r\n");
      out.write("\r\n");
      out.write("\t\tauto_down_flag = false;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (innomp_detected_os == \"Linux x86_64\") {\r\n");
      out.write("\t\tinnomp_download_file = \"innogmp7-7.2.0.582-x64.rpm\";\r\n");
      out.write("\t\tinnomp_download_file2 = \"innogmp7-7.2.0.582-x64.deb\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for Linux(.RPM)\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str2\").innerHTML = \"Version 7.2 for Linux(.DEB)\";\r\n");
      out.write("\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').style.cssText = \"float:left; margin-left:30px\";\r\n");
      out.write("\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link2').href = Install.InnoPackage + \"/\" + innomp_download_file2;\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link2').style.cssText = \"display:inline; float:left; margin-left:10px\";\r\n");
      out.write("\r\n");
      out.write("\t\tauto_down_flag = false;\r\n");
      out.write("        // var str = 'install command <br /> sudo bash /download-path/InnoGMP_Linux_x64.bin';\r\n");
      out.write("\t\t// var extraTr = document.getElementById(\"extra_tr\");\r\n");
      out.write("\t\t// extraTr.className = \"on\";\r\n");
      out.write("\t\t// extraTr.children[0].innerHTML = str;\r\n");
      out.write("        // auto_down_flag = false;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (innomp_detected_os == \"MacIntel\") {\r\n");
      out.write("\t\tinnomp_download_file = \"InnoGMP_Mac_Intel.pkg\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for Intel\";\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (innomp_detected_os == \"MacPPC\") {\r\n");
      out.write("\t\tinnomp_download_file = \"InnoGMP_Mac_PPC.pkg\";\r\n");
      out.write("\t\tdocument.getElementById(\"innomp_download_str\").innerHTML = \"Version 7.2 for PPC\";\r\n");
      out.write("\t\tdocument.getElementById('innomp_download_link').href = Install.InnoPackage + \"/\" + innomp_download_file;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("    if (auto_down_flag) {\r\n");
      out.write("\t\tdocument.write('<META HTTP-EQUIV=\"Refresh\" CONTENT=\"3; URL='+Install.InnoPackage+\"/\"+innomp_download_file+'\"/>');\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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