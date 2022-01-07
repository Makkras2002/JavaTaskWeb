package com.makkras.shop.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" })
public class ScriptInjectionFilter implements Filter {
    private static final String INJECTION_INDICATOR_RIGHT = ">";
    private static final String INJECTION_INDICATOR_LEFT = "<";
    private static final String NEW_SYMBOL = "";
    public static class FilteredRequest extends HttpServletRequestWrapper {
        public FilteredRequest(ServletRequest request) {
            super((HttpServletRequest)request);
        }

        public String sanitize(String input) {
            String result = input;
            if(result != null) {
                if (result.contains(INJECTION_INDICATOR_LEFT)) {
                    result = result.replaceAll(INJECTION_INDICATOR_LEFT,NEW_SYMBOL);
                }
                if(result.contains(INJECTION_INDICATOR_RIGHT)) {
                    result = result.replaceAll(INJECTION_INDICATOR_RIGHT,NEW_SYMBOL);
                }
            }
            return result;
        }

        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            return sanitize(value);
        }

        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
            for (int index = 0; index < values.length; index++) {
                values[index] = sanitize(values[index]);
            }
            return values;
        }
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FilteredRequest(request), response);
    }
}
