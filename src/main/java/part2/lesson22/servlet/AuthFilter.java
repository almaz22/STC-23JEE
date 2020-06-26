package part2.lesson22.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter
 *
 * @author Almaz_Kamalov
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    private final Logger LOGGER = LogManager.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login";

        //Если сессия ранее создана
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);

        if(loggedIn && loginRequest) {
            LOGGER.info("AuthFilter new if");
            req.getSession().setAttribute("user", null);
            filterChain.doFilter(req, resp);
        } else {
            if (loggedIn || loginRequest) {
                LOGGER.info("AuthFilter true");
                filterChain.doFilter(req, resp);
            } else {
                LOGGER.info("AuthFilter false");
                resp.sendRedirect(loginURI);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
