package uz.java_ee.servlets;

import uz.java_ee.configs.ThreadPoolExecutorConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class HomeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ThreadPoolExecutorConfig.run();
    }

    /**
     * HTTP VERBS
     * GET - read
     * POST - create, save
     * PUT - update
     * PATCH - update(partially)
     * DELETE - delete
     * OPTION - ?
     * HEAD - ?
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dp = req.getRequestDispatcher("views/main.jsp");
        boolean loggedId = Objects.isNull(req.getSession().getAttribute("userId"));
        req.setAttribute("loggedId", loggedId);
        dp.forward(req, resp);
    }


    @Override
    public void destroy() {
        // ThreadPoolExecutorConfig.shutDown();
    }
}
