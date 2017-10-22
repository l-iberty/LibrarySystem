package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckBookServlet extends HttpServlet {
    private final String CHECKBOOK_PAGE = "/MyPage/CheckBook.jsp";
    private final String CURRENT_PAGE = "/MyPage/LoginSuccess.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String key = request.getParameter("key"); // `key` belongs to { "name", "author" }
        String keyvalue = request.getParameter("keyvalue");

        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        Map<String, Map<String, Object>> result;
        List<Book> books;
        BookHelper helper = new BookHelper();
        BookEntries bookEntries = new BookEntries();

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (id != null && !id.trim().equals("")) {
            result = dataBase.query("book", "*", "id=\'" + id + "\'");
            books = helper.getBookList(result);
            bookEntries.setEntries(helper.getDetailedInfo(books));

            dispatcher = request.getRequestDispatcher(CHECKBOOK_PAGE);
        }

        if (keyvalue != null && !keyvalue.trim().equals("") &&
                dispatcher == null) {
            switch (key) {
                case "name":
                    result = dataBase.query("book",
                            "*",
                            "name=\'" + keyvalue + "\'");
                    books = helper.getBookList(result);
                    bookEntries.setEntries(helper.getBasicInfo(books));
                    break;
                case "author":
                    result = dataBase.query("book",
                            "*",
                            "author=\'" + keyvalue + "\'");
                    books = helper.getBookList(result);
                    bookEntries.setEntries(helper.getBasicInfo(books));
                    break;
            }
            dispatcher = request.getRequestDispatcher(CHECKBOOK_PAGE);
        }

        if (dispatcher == null) {
            dispatcher = request.getRequestDispatcher(CURRENT_PAGE);
        }

        session.setAttribute("bookEntries2", bookEntries);
        dispatcher.forward(request, response);
    }
}
