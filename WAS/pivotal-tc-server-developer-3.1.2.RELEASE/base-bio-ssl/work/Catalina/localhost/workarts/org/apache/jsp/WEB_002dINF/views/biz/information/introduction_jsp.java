/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/7.0.64.B.RELEASE
 * Generated at: 2016-06-04 07:25:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.biz.information;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import framework.util.AbleUtil;
import org.joda.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class introduction_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<h1 class=\"tit\">예술제소개</h1>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/layouts/biz/common/breadcrumb.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"intro-wrap\">\t\t\r\n");
      out.write("\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/txt-intro.png\" alt=\"\" />\r\n");
      out.write("\t<p class=\"blind\">\r\n");
      out.write("\t\t고용노동부와 근로복지공단 그리고 KBS한국방송이 함께 마련한 근로자 문화예술제는 1971년 5월 10일 노동부가 이땅에서 일하는 나이어린 일꾼들에게 삶의 보람과 꿈을 심어주기 위해 '여자와 소년근로자 생활수기 현상공모' 라는 다소 긴 이름의 행사를 펼친 것이 그 씨앗이 되었습니다.\r\n");
      out.write("\t\t이후 1980년 3월 10일 근로자의 날을 맞아 대구지방 노동청이 서예, 공예, 수예 3개 부문에 걸쳐 관내 여성 근로자의 출품작을 받아 '근로자 여성 연합작품 전시회 및 바자회'를 연 것이 계기가 되어 같은 해 11월 서울 세종문화회관에서 제1회 노동문화제가 탄생하게 되었습니다.\r\n");
      out.write("\t\t1980년 11월 '제1회 노동문화제'(미술분야)가 개최된데 이어 1986년 부터는 근로복지공단과 KBS한국방송이 공동으로 \r\n");
      out.write("\t\t노동 문화제를 개최하여 왔으며, 1992년 1월 노동문화제에서 '근로자 문화예술제'로 명칭을 변경하고 참가대상을 \r\n");
      out.write("\t\t모든 근로자로 확대(5인 이상 사업장에서 1인 이상 사업장)하여 실시해 오고 있습니다.\r\n");
      out.write("\t</p>\r\n");
      out.write("\t<div class=\"purpose\">\r\n");
      out.write("\t\t<h2 class=\"tit\">예술제 목적</h2>\r\n");
      out.write("\t\t<p class=\"txt\"><span class=\"pt\">근로자에게 다양한 문화예술활동의 장 마련</span><span style=\"text-decoration:none;\">으로</span></br><span>근로자의 정서 함양과 삶의 질 향상에 기여,</br>\r\n");
      out.write("\t\t건전한 노동문화 창출로 창의적 여가활용 유도와 근로의욕 증대,</br>\r\n");
      out.write("\t\t노사간 문화공감대 형성을 통항 노사화합 도모</span></p>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"history\">\r\n");
      out.write("\t\t<h2 class=\"tit\">예술제 연혁</h2>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1980</span><span class=\"month\">11월</span>제1회 노동문화제 '미술분야' 개최(고용노동부 주최)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1981</span><span class=\"month\">5월</span>문학분야 신설, 국무총리상 제정(제2회)</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1983</span><span class=\"month\">5월</span>수상자 산업 및 해외문화 시찰 신설</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1985</span><span class=\"month\">5월</span>음악분야 한국경영자총협회 주관,<span class=\"block\">KBS '근로자가요제'란 타이틀로 방송(제6회)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1986</span><span class=\"month\">9월</span>노동문화제 '미술·문학' 사업이관(고용노동부→근로복지공사)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1987</span><span class=\"month\">5월</span>국무총리 지시 제14호에 의거 행사주최기관 변경<span class=\"block\">  (고용노동부 → 근로복지공사)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1988</span><span class=\"month\">10월</span>연극분야 신설(제9회)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1990</span><span class=\"month\">10월</span>음악분야 주관기관 변경<span class=\"block wide\">(한국경영자총협회 → 한국방송공사)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1992</span><span class=\"month\">4월</span>명칭변경 노동문화제 → 근로자문화예술제(제13회)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1993</span><span class=\"month\">7월</span>음악분야 주관기관 변경<span class=\"block\">한국방송공사 → KBS문화사업단(제14회)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1996</span><span class=\"month\">5월</span>부대행사 '근로자백일장', '노사한마당음악제' 신설</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">1996</span><span class=\"month\">6월</span>대통령상 신설(국무총리상에서 격상)</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">1998</span><span class=\"month\">7월</span>국무총리상 부활</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2000</span><span class=\"month\">12월</span>근로자문화예술제 21년사 발간</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2002</span><span class=\"month\">1월</span>참가대상 확대 (6월 이내 실직자 및 산재요양 승인된 자 포함)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2002</span><span class=\"month\">2월</span>근로자문화예술제 추천작가제도 신설</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2005</span><span class=\"month\">3월</span>행사 전분야 일괄 위탁운영 실시(KBS미디어)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2007</span><span class=\"month\">1월</span>참가대상 변경(재직근로자 및 산재로 요양중인 자)</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2007</span><span class=\"month\">1월</span>문학분야 4개 부문으로 통합</br>(단편소설 및 콩트를 1개 부문으로 통합) </li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2011</span><span class=\"month\">2월</span>제32회 근로자문화예술제</li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2012</span><span class=\"month\">2월</span>제33회 근로자문화예술제</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2013</span><span class=\"month\">2월</span>제34회 근로자문화예술제<span class=\"block\">(시조, 동화, 시나리오 분야 도입)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2014</span><span class=\"month\">2월</span>제35회 근로자문화예술제 (ARS, UCC 접수 도입)</li>\r\n");
      out.write("\t\t\t<li class=\"even\"><span class=\"year\">2015</span><span class=\"month\">2월</span>제36회 근로자문화예술제<span class=\"block\">(디자인, 캘리그라피, 단편드라마 분야 도입 /</br>회화부분 민화 추가, 문학부분 콩트 삭제)</span></li>\r\n");
      out.write("\t\t\t<li class=\"odd\"><span class=\"year\">2016</span><span class=\"month\">2월</span>제37회 근로자문화예술제</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div><!-- //.intro-wrap -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction notice_getCookie( name ){\r\n");
      out.write("\t\tvar nameOfCookie = name + \"=\";\r\n");
      out.write("\t\tvar x = 0;\r\n");
      out.write("\t\twhile ( x <= document.cookie.length )\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t\tvar y = (x+nameOfCookie.length);\r\n");
      out.write("\t\t\t\tif ( document.cookie.substring( x, y ) == nameOfCookie ) {\r\n");
      out.write("\t\t\t\t\t\tif ( (endOfCookie=document.cookie.indexOf( \";\", y )) == -1 )\r\n");
      out.write("\t\t\t\t\t\t\t\tendOfCookie = document.cookie.length;\r\n");
      out.write("\t\t\t\t\t\treturn unescape( document.cookie.substring( y, endOfCookie ) );\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tx = document.cookie.indexOf( \" \", x ) + 1;\r\n");
      out.write("\t\t\t\tif ( x == 0 )\r\n");
      out.write("\t\t\t\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn \"\";\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//팝업 띄우기\r\n");
      out.write("\t\r\n");
      out.write("\t//if ( notice_getCookie( \"Notice3\" ) != \"done\" )\r\n");
      out.write("\t//{\r\n");
      out.write("\t//\twindow.open('/design/popup_20150813.php','Notice3','left=395,top=0,width=521,height=285'); // 팝업윈도우의 경로와 크기를 설정 하세요\r\n");
      out.write("\t//}\r\n");
      out.write("\t\r\n");
      out.write("\t//if ( notice_getCookie( \"Notice2\" ) != \"done\" )\r\n");
      out.write("\t//{\r\n");
      out.write("\t//\twindow.open('/design/popup_20150720_A.php','Notice2','left=0,top=0,width=378,height=432'); // 팝업윈도우의 경로와 크기를 설정 하세요\r\n");
      out.write("\t//}\r\n");
      out.write("\t//\r\n");
      out.write("\tif ( notice_getCookie( \"Notice1\" ) != \"done\" )\r\n");
      out.write("\t{\r\n");
      out.write("\t\twindow.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/popup','Notice1','left=0,top=0,width=444,height=568'); // 팝업윈도우의 경로와 크기를 설정 하세요\r\n");
      out.write("\t}\r\n");
      out.write("\t//window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/popup2','Notice2','left=0,top=0,width=701,height=333'); // 팝업윈도우의 경로와 크기를 설정 하세요\r\n");
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