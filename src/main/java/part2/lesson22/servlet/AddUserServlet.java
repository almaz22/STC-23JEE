package part2.lesson22.servlet;

import part2.lesson22.dao.UserDao;
import part2.lesson22.pojo.Mobile;
import part2.lesson22.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddUserServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New User");
        req.setAttribute("PageBody", "adduser.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(null, username, password);
        userDao.addUser(user);

        resp.sendRedirect(req.getContextPath() + "/allusers");
    }
}
