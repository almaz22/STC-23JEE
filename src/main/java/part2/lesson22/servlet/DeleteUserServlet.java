package part2.lesson22.servlet;

import part2.lesson22.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteUserServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        userDao.deleteUser(Integer.valueOf(id));

        resp.sendRedirect(req.getContextPath() + "/allusers");
    }
}
