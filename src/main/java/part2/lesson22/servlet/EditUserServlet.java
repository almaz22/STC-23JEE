package part2.lesson22.servlet;

import part2.lesson22.dao.MobileDao;
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
 * EditUserServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if (userId == null) {
            throw new ServletException("Missing parameter id");
        }
        User user = userDao.getUserById(Integer.valueOf(userId));
        if (user == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "Mobiles");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        req.setAttribute("user", user);
        req.setAttribute("PageTitle", "Edit User");
        req.setAttribute("PageBody", "edituser.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        userDao.updateUser(Integer.valueOf(id), username, password);

        resp.sendRedirect(req.getContextPath() + "/allusers");
    }

}
