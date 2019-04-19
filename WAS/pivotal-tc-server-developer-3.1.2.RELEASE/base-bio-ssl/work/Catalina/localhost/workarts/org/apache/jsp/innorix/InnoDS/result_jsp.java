/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Pivotal tc Runtime 3.1.2.RELEASE/7.0.64.B.RELEASE
 * Generated at: 2016-06-03 12:02:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.innorix.InnoDS;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class result_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
request.setCharacterEncoding("utf-8");
      out.write("\n");
      out.write("<html>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("<head>\n");
      out.write("<title>Innorix File Transfer Solution</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<font size=\"5\"><b>전송결과</b></font> - <a href=\"javascript:;\" onclick=\"history.back();\">다른파일 전송하기</a>\n");
      out.write("<br /><br />\n");
      out.write("\n");
      out.write("test1 : ");
      out.print(request.getParameter("test1"));
      out.write("<br /><br />\n");
      out.write("\n");
      out.write("<table width=\"570\" border=\"0\" cellspacing=\"1\" cellpadding=\"3\" bgcolor=\"#c0c0c0\">\n");
      out.write("<tr bgcolor=\"#ebe8d6\" align=\"center\" height=\"25\">\n");
      out.write("\t<td nowrap>원본 파일명</td>\n");
      out.write("\t<td nowrap>저장 파일명</td>\n");
      out.write("\t<td nowrap>저장 폴더명</td>\n");
      out.write("\t<td nowrap>파일 사이즈</td>\n");
      out.write("\t<td nowrap>개발자 정의값</td>\n");
      out.write("\t<td nowrap>컴포넌트 ID</td>\t\n");
      out.write("</tr>\n");

request.setCharacterEncoding("utf-8");
String[] origfilename = request.getParameterValues("_innorix_origfilename"); 	// 원본 파일명
String[] savefilename = request.getParameterValues("_innorix_savefilename"); 	// 저장 파일명
String[] savepath = request.getParameterValues("_innorix_savepath"); 			// 파일 저장경로
String[] filesize = request.getParameterValues("_innorix_filesize"); 			// 파일 사이즈
String[] foldername = request.getParameterValues("_innorix_folder"); 			// 폴더정보(폴더 업로드시만)
String[] customvalue = request.getParameterValues("_innorix_customvalue"); 		// 개발자 정의값
String[] componentname = request.getParameterValues("_innorix_componentname"); 	// 컴포넌트 이름

if (origfilename != null)
{
	for (int i = 0; i < origfilename.length; i++)
	{
		/* 여기에 업로드 파일 정보를 DB에 입력하는 코드를 작성 합니다. */

      out.write("\n");
      out.write("<tr bgcolor=\"#ffffff\" align=\"left\" height=\"22\">\n");
      out.write("\t<td nowrap>");
      out.print(origfilename[i]);
      out.write("</td>\n");
      out.write("\t<td nowrap>");
      out.print(savefilename[i]);
      out.write("</td>\n");
      out.write("\t<td nowrap>");
      out.print(savepath[i]);
      out.write("</td>\n");
      out.write("\t<td nowrap align=\"right\">");
      out.print(filesize[i]);
      out.write("</td>\n");
      out.write("\t<td nowrap>");
      out.print(customvalue[i]);
      out.write("</td>\n");
      out.write("\t<td nowrap>");
      out.print(componentname[i]);
      out.write("</td>\n");
      out.write("</tr>\n");

	}
}

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");

String[] strExistID = request.getParameterValues("_innorix_exist_id"); 		// 존재하는 파일ID
String[] strExistName = request.getParameterValues("_innorix_exist_name");	// 존재하는 파일이름
String[] strExistSize = request.getParameterValues("_innorix_exist_size");	// 존재하는 파일용량

if (strExistID != null)
{

      out.write("\n");
      out.write("<br />\n");
      out.write("<table width=\"570\" border=\"0\" cellspacing=\"1\" cellpadding=\"3\" bgcolor=\"#c0c0c0\">\n");
      out.write("<tr bgcolor=\"#ebe8d6\" align=\"center\" height=\"25\">\n");
      out.write("\t<td nowrap>존재하는 파일ID</td>\n");
      out.write("\t<td nowrap>존재하는 파일이름</td>\n");
      out.write("\t<td nowrap>존재하는 파일용량</td>\n");
      out.write("</tr>\n");

	for (int i = 0; i < strExistID.length; i++)
	{
		/* 여기에 삭제된 파일 정보를 받아 파일을 삭제하는 코드를 작성 */

      out.write("\n");
      out.write("<tr bgcolor=\"#ffffff\" align=\"left\" height=\"22\">\n");
      out.write("\t<td><strong></strong>");
      out.print(strExistID[i]);
      out.write("</td>\n");
      out.write("\t<td>");
      out.print(strExistName[i]);
      out.write("</td>\n");
      out.write("\t<td align=\"right\">");
      out.print(strExistSize[i]);
      out.write("</td>\n");
      out.write("</tr>\n");

	}
}

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");

String[] strDeletedID = request.getParameterValues("_innorix_deleted_id"); // 삭제된 파일ID
String[] strDeletedName = request.getParameterValues("_innorix_deleted_name"); // 삭제된 파일이름
String[] strDeletedSize = request.getParameterValues("_innorix_deleted_size"); // 삭제된 파일용량

if (strDeletedID != null)
{

      out.write("\n");
      out.write("<br />\n");
      out.write("<table width=\"570\" border=\"0\" cellspacing=\"1\" cellpadding=\"3\" bgcolor=\"#c0c0c0\">\n");
      out.write("<tr bgcolor=\"#ebe8d6\" align=\"center\" height=\"25\">\n");
      out.write("\t<td nowrap>삭제된 파일ID</td>\n");
      out.write("\t<td nowrap>삭제된 파일이름</td>\n");
      out.write("\t<td nowrap>삭제된 파일용량</td>\n");
      out.write("</tr>\n");

	for (int i = 0; i < strDeletedID.length; i++)
	{
		/* 여기에 삭제된 파일 정보를 받아 파일을 삭제하는 코드를 작성 */

      out.write("\n");
      out.write("<tr bgcolor=\"#ffffff\" align=\"left\" height=\"22\">\n");
      out.write("\t<td><strong></strong>");
      out.print(strDeletedID[i]);
      out.write("</td>\n");
      out.write("\t<td>");
      out.print(strDeletedName[i]);
      out.write("</td>\n");
      out.write("\t<td align=\"right\">");
      out.print(strDeletedSize[i]);
      out.write("</td>\n");
      out.write("</tr>\n");

	}
}

      out.write("\n");
      out.write("</table>\n");
      out.write("</body>\n");
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
}
