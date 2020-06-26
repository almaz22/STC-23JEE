package part2.lesson22.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson22.dao.MobileDao;
import part2.lesson22.dao.UserDao;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private final Logger LOGGER = LogManager.getLogger(AppContextListener.class);
    @Inject
    private MobileDao mobileDao;
    @Inject
    private UserDao userDao;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String isDao = servletContext.getInitParameter("isDao");
        if (isDao.equals("true")) {
            servletContext.setAttribute("dao", mobileDao);
            LOGGER.info("Added attribute DAO");
        }
        String isUserDao = servletContext.getInitParameter("isUserDao");
        if (isUserDao.equals("true")) {
            servletContext.setAttribute("userDao", userDao);
            LOGGER.info("Added attribute UserDAO");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("dao");
        servletContext.removeAttribute("userDao");
        LOGGER.info("Removed attribute DAO");
    }
}
