package part2.lesson22.servlet;

import part2.lesson22.dao.UserDao;
import part2.lesson22.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * AllUsersServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/allusers")
public class AllUsersServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = userDao.getAllUsers();
        req.setAttribute("users", users);
        req.setAttribute("PageTitle", "Users");
        req.setAttribute("PageBody", "allusers.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }


}
