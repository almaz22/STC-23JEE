package part2.lesson22.servlet;

import part2.lesson22.dao.MobileDao;
import part2.lesson22.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EditMobileServlet
 *
 * @author Almaz_Kamalov
 */
@WebServlet("/editmobile")
public class EditMobileServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = mobileDao.getMobileById(Integer.valueOf(mobileId));
        if (mobile == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "Mobiles");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        req.setAttribute("mobile", mobile);
        req.setAttribute("PageTitle", "Edit Mobiles");
        req.setAttribute("PageBody", "editform.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        mobileDao.updateMobile(Integer.valueOf(id), model, Integer.valueOf(price), manufacturer);

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }

}
