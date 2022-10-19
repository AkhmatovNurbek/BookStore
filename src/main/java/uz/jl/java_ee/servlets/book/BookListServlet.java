package uz.jl.java_ee.servlets.book;

import uz.jl.java_ee.container.ApplicationContext;
import uz.jl.java_ee.dao.BookDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/books")
public class BookListServlet extends HttpServlet {

    private final static BookDao bookDao = ApplicationContext.getBean(BookDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookDao.findAll());
        RequestDispatcher dp = req.getRequestDispatcher("views/book/list.jsp");
        dp.forward(req, resp);
    }
}
