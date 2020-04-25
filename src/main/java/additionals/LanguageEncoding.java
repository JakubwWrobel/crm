package additionals;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(filterName = "LanguageEncoding", urlPatterns = {"/*"})
public class LanguageEncoding implements Filter {
    public static final String UTF_8 = "UTF-8";
    public void destroy() {
        // not used
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(UTF_8);
        resp.setCharacterEncoding(UTF_8);
        chain.doFilter(req, resp);
    }
    public void init(FilterConfig config) throws ServletException {
        // not used
    }
}
