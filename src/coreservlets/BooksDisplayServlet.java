package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BooksDisplayServlet extends HttpServlet {
    private final String BOOK_DISPLAY_JSP_PAGE = "/MyPage/LoginSuccess.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        Map<String, Map<String, Object>> result =
                dataBase.query("book", "*", null);

        BookHelper helper = new BookHelper();
        List<Book> books = helper.getBookList(result);
        BookEntries bookEntries = new BookEntries();
        bookEntries.setEntries(helper.getBasicInfo(books));

        HttpSession session = request.getSession();
        session.setAttribute("bookEntries", bookEntries);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(BOOK_DISPLAY_JSP_PAGE);
        dispatcher.forward(request, response);
    }
}
