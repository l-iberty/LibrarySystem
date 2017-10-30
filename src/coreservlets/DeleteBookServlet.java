package coreservlets;

import coreservlets.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteBookServlet extends HttpServlet {
    private final String DELBOOK_OK_PAGE = "/MyPage/DeleteBookOk.jsp";
    private final String DELBOOK_FAIL_PAGE = "/MyPage/DeleteBookFail.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");

        String id = request.getParameter("id");
        if (id != null && !id.trim().equals("")) {
            if (!dataBase.query("book", "*", "id=\'" + id + "\'").isEmpty()) {
                dataBase.delete("book", "id=\'" + id + "\'");
                // 动态修改 LoginSuccess.jsp 页面的图书列表
                new BookHelper().informDisplay(request.getSession(), dataBase);
                dispatcher = request.getRequestDispatcher(DELBOOK_OK_PAGE);
                dispatcher.forward(request, response);
                return;
            }
        }

        dispatcher = request.getRequestDispatcher(DELBOOK_FAIL_PAGE);
        dispatcher.forward(request, response);

    }
}
