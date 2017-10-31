package coreservlets;

import coreservlets.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddBookServlet extends HttpServlet {
    private final String ADDBOOK_FAIL_PAGE = "/MyPage/AddBookFail.jsp";
    private final String ADDBOOK_OK_PAGE = "/MyPage/AddBookOk.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = 0.0;
        int numItems = 0;
        int numSales = 0; // 销量为零
        try {
            price = Double.parseDouble(request.getParameter("price"));
            numItems = Integer.parseInt(request.getParameter("numItems"));
        } catch (NumberFormatException nfe) {
            dispatcher = request.getRequestDispatcher(ADDBOOK_FAIL_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        if (price <= 0.0 || numItems <= 0.0) {
            dispatcher = request.getRequestDispatcher(ADDBOOK_FAIL_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        dataBase.insert("book",
                "(id,name,author,price,numItems,numSales)",
                "(\'" + id + "\'," +
                        "\'" + name + "\'," +
                        "\'" + author + "\'," +
                        price + "," +
                        numItems + "," +
                        numSales + ")");

        // 动态修改 LoginSuccess.jsp 页面的图书列表
        new BookManager().informDisplay(request.getSession(), dataBase);

        dispatcher = request.getRequestDispatcher(ADDBOOK_OK_PAGE);
        dispatcher.forward(request, response);
    }
}
