/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/7.0.64.B.RELEASE
 * Generated at: 2016-02-04 13:36:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.biz.festival.survey;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/app/biz/festival/survey/survey.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<div class=\"ly-survey-wrap\" style=\"display:block;\">\r\n");
      out.write("\t<div class=\"ly-survey\" style=\"display:block;\">\r\n");
      out.write("\t\t<h2>설문조사</h2>\r\n");
      out.write("\t\t<span class=\"txt-survey\">더 나은 문화예술제를 만들기 위해 아래와 같이 설문조사를 작성해 주시면 감사하겠습니다.</span>\r\n");
      out.write("\t\t<form action=\"\" id=\"frmSurvey\" name=\"frmSurvey\" method=\"post\" class=\"frm-survey\">\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t<ul class=\"survey-list\">\r\n");
      out.write("\t\t\t\t\t<li class=\"qa01\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"txt-qu\">참가 경로는?</span>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"txt-an\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"radio-list ofh\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"TV\" id=\"way01\" /><label for=\"way01\">TV광고</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"INTERNET\" id=\"way02\" /><label for=\"way02\">인터넷</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"POSTER\" id=\"way03\" /><label for=\"way03\">개최포스터</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"ADVICE\" id=\"way04\" /><label for=\"way04\">동료권유</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"MAGAZINE\" id=\"way05\" /><label for=\"way05\">잡지</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"PARTICIPANT\" id=\"way06\" /><label for=\"way06\">예전참가자</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_path\" value=\"ETC\" id=\"way07\" /><label for=\"way07\">기타</label><input type=\"text\" name=\"participation_path_etc\" class=\"txt-etc\" title=\"기타입력\" /></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div> \r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"qa02\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"txt-qu\"><label for=\"apply_cnt\">문화예술제 참가횟수는?</label></span>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"txt-an\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"participation_number\" numberonly=\"true\" id=\"apply_cnt\" /><span>회 (금년 포함)</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"qa03\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"txt-qu\">문화예술제 참가동기는?</span>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"txt-an\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"radio-list ofh\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_motivation\" value=\"REALIZATION\" id=\"way08\" /><label for=\"way08\">예술활동을 통한 자아실현</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_motivation\" value=\"ADVICE\" id=\"way09\" /><label for=\"way09\">주변권유</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_motivation\" value=\"PRIZE\" id=\"way10\" /><label for=\"way10\">시상금 수령</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_motivation\" value=\"EXPERIENCE\" id=\"way11\" /><label for=\"way11\">해외문화 체험</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"participation_motivation\" value=\"ETC\" id=\"way12\" /><label for=\"way12\">기타</label><input type=\"text\" name=\"participation_motivation_etc\" class=\"txt-etc\" title=\"기타입력\" /></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div> \r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"qa04\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"txt-qu\">문화예술제의 발전을 위해 가장 필요한 것은?</span>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"txt-an\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"radio-list ofh\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"requirement\" value=\"PRIZE\" id=\"way13\" /><label for=\"way13\">시상금인상</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"requirement\" value=\"QUALIFICATION\" id=\"way14\" /><label for=\"way14\">참가자격 완화</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"requirement\" value=\"PROMOTION\" id=\"way15\" /><label for=\"way15\">홍보강화</label></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"requirement\" value=\"FIELD\" id=\"way16\" /><label for=\"way16\">분야확대</label>(<label for=\"part\">확대필요분야</label><input type=\"text\" name=\"requirement_enlargement\" id=\"part\" style=\"width:55px;\" />)</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><input type=\"radio\" name=\"requirement\" value=\"ETC\" id=\"way17\" /><label for=\"way17\">기타</label><input type=\"text\" name=\"requirement_etc\" id=\"\" class=\"txt-etc\" style=\"width:70px;\" /></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div> \r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"qa05\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"txt-qu\"><label for=\"txt_cont\">문화예술제와 관련하여 하고 싶은말</label></span>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"txt-an\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea name=\"suggestion\" id=\"txt_cont\" class=\"txtarea\"></textarea>\t\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"txt-cnt\"><strong id=\"spanWordCount\">0</strong> / 500자</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul><!-- //.survey-list -->\r\n");
      out.write("\t\t\t\t<div class=\"btn-wrap\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" onclick=\"surveyValidation(); return false;\" class=\"btn-bcom\">완료</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t</form><!-- //.frm-survey -->\r\n");
      out.write("\t</div><!-- //.ly-survey -->\r\n");
      out.write("</div><!-- //.ly-survey-wrap -->\r\n");
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
