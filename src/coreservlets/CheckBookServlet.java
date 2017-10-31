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
        Map<String, Map<String, Object>> result = null;
        List<Book> books;
        BookManager bookManager=new BookManager();

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (id != null && !id.trim().equals("")) {
            result = dataBase.query("book", "*", "id=\'" + id + "\'");
            if (!result.isEmpty()) {
                books = bookManager.getBookList(result);
                bookManager.setEntries(bookManager.getDetailedInfo(books));
            } else {
                bookManager.setEntries("No books found!");
            }
            dispatcher = request.getRequestDispatcher(CHECKBOOK_PAGE);
        }

        if (keyvalue != null && !keyvalue.trim().equals("") &&
                dispatcher == null) {
            switch (key) {
                case "name":
                    result = dataBase.query("book",
                            "*",
                            "name=\'" + keyvalue + "\'");
                    break;
                case "author":
                    result = dataBase.query("book",
                            "*",
                            "author=\'" + keyvalue + "\'");
                    break;
            }
            if (result != null && !result.isEmpty()) {
                books = bookManager.getBookList(result);
                bookManager.setEntries(bookManager.getBasicInfo(books));
            } else {
                bookManager.setEntries("No books found!");
            }
            dispatcher = request.getRequestDispatcher(CHECKBOOK_PAGE);
        }

        if (dispatcher == null) {
            dispatcher = request.getRequestDispatcher(CURRENT_PAGE);
        }

        session.setAttribute("bookManager2", bookManager);
        dispatcher.forward(request, response);
    }
}
