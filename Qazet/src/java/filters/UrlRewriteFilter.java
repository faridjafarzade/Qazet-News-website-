/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Farid
 */
@WebFilter(filterName = "UrlRewriteFilter", urlPatterns = {"/*"})
public class UrlRewriteFilter implements Filter {
    
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public UrlRewriteFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("UrlRewriteFilter:DoBeforeProcessing");
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
            log("UrlRewriteFilter:DoAfterProcessing");
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
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest srequest = (HttpServletRequest) request;
        HttpServletResponse sresponse = (HttpServletResponse) response;
        final String topicName = srequest.getParameter("topic");
        final String newsName = srequest.getParameter("news");   
        String url = srequest.getRequestURI().trim();
 
        // Process the written URL in the form
        // http://tld.com/myapp/store/specials/value_item_description
        // Forward to the original page silently without
        // the knowledge of the browser, URL displayed in
        // browser remains the same.
        //
        // In our case, before we forward the page
        // we first make sure the descriptive text part
        // of the URL was not changed by comparing it
        // against the database record and
        // do a redirect if it was changed and then
        // takes us back here eventually.
  
        if (url.contains(topicName+"/"+newsName)) {
            StringBuilder forward = new StringBuilder();                       
            forward.append("/article.xhtml?");
            forward.append(srequest.getQueryString());
            request.getRequestDispatcher(forward.toString()).forward(request, response);
             
        // Process access to the original URL in the form
        // http://tld.com/myapp/store_page.xhtml?item=value&subitem=value2 
        // This takes another loop to this filter but to the if part of this
        // block.  The browser is aware of the redirect and URL displayed
        // in the browser is rewritten.
             
        } else if (url.contains("/article.xhtml")) {
            sresponse.setStatus(301);
           // sresponse.addHeader("NN/" + rebuildUrl(srequest), url);
            //sresponse.sendRedirect("NN/" + rebuildUrl(srequest));
        } else {           
            chain.doFilter(srequest, sresponse);
        }
 
    }
     
    String rebuildUrl (HttpServletRequest srequest) {
         
        
        final String topicName = srequest.getParameter("topic");
        final String newsName = srequest.getParameter("news");  
        String description = "retrieve else";
         
        // In our case, we included itemValue to the rewritten
        // URL because itemValue is the key to our database. We retrieve
        // page data based on itemValue.
 
        description = topicName+"/"+
          newsName;
         
        // replaceAll above replaces all non alpha-numeric characters in
        // the description with an underscore. 
                
        return description;
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
                log("UrlRewriteFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("UrlRewriteFilter()");
        }
        StringBuffer sb = new StringBuffer("UrlRewriteFilter(");
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
