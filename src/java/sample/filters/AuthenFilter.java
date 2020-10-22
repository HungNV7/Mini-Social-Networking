/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dtos.AccountDTO;

import org.apache.log4j.Logger;

/**
 *
 * @author ASUS
 */
public class AuthenFilter implements Filter {

    private static final boolean debug = true;
    private List<String> member;
    private List<String> others;
    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    private final Logger logger = Logger.getLogger(AuthenFilter.class.getName());

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenFilter() {
        member = new ArrayList<String>();

        member.add("");
        member.add("error.jsp");
        member.add("search.jsp");
        member.add("detail.jsp");
        member.add("create.jsp");
        member.add("SearchController");
        member.add("DetailController");
        member.add("CreateController");
        member.add("LogoutController");
        member.add("CommentController");
        member.add("EmotionController");
        member.add("DeletePostController");
        member.add("DeleteCommentController");
        member.add("notify.jsp");
        member.add("NotifyController");
        member.add("DetailNotifyController");

        others = new ArrayList<String>();

        others.add("");
        others.add("login.jsp");
        others.add("error.jsp");
        others.add("register.jsp");
        others.add("LoginController");
        others.add("RegisterController");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            String uri = req.getRequestURI();

            if (uri.contains(".jpg") || uri.contains(".png")) {
                chain.doFilter(request, response);
            } else {
                if (uri.contains("MainController")) {
                    chain.doFilter(request, response);
                } else {
                    int index = uri.lastIndexOf("/");
                    String resource = uri.substring(index + 1);

                    if (session == null || session.getAttribute("ACCOUNT") == null) {
                        if (others.contains(resource)) {
                            chain.doFilter(request, response);
                        } else {
                            if (session.getAttribute("EMAIL") != null && (resource.contains("verify.jsp") || resource.contains("VerifyController"))) {
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(LOGIN_PAGE);
                            }
                        }
                    } else {
                        AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                        String role = account.getRole();

                        if (role.equals("Member")) {
                            if (member.contains(resource)) {
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(SEARCH_PAGE);
                            }
                        } else {
                            res.sendRedirect(LOGIN_PAGE);
                        }
                    }

                }
            }

        } catch (Exception e) {
            logger.error("Error in AuthenFilter: " + e);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
