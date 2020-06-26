package part2.lesson22.servlet;

import part2.lesson22.dao.UserDao;
import part2.lesson22.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Welcome Page");
        req.setAttribute("PageBody", "loginpage.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user =  userDao.getUserByName(username);

        if (user != null && user.getPassword().equals(password)) {
            req.setCharacterEncoding("utf-8");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/mobiles");
        } else {
            req.setAttribute("statusCode", 403);
            req.setAttribute("requestUri", req.getRequestURI());
            req.getRequestDispatcher("/accessdenied.jsp")
                    .forward(req, resp);
        }
    }

}
