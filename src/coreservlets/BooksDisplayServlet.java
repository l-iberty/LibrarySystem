package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BooksDisplayServlet extends HttpServlet {
    private final String BOOK_DISPLAY_PAGE = "/MyPage/LoginSuccess.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        new BookManager().informDisplay(request.getSession(), dataBase);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(BOOK_DISPLAY_PAGE);
        dispatcher.forward(request, response);
    }
}
