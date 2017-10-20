package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PurchaseBookServlet extends HttpServlet {
    private final String PURCHASE_OK_JSP_PAGE = "/MyPage/PurchaseOk.jsp";
    private final String PURCHASE_FAIL_JSP_PAGE = "/MyPage/PurchaseFail.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String szNumPurchase = request.getParameter("numPurchase");
        int numPurchase = 0;
        if (szNumPurchase != null && !szNumPurchase.trim().equals("")) {
            try {
                numPurchase = Integer.parseInt(szNumPurchase);
            } catch (NumberFormatException nfe) {
                // do nothing
            }
        }

        RequestDispatcher dispatcher;
        Book book = purchase(name, numPurchase);
        if (book != null) {
            HttpSession session = request.getSession();
            session.setAttribute("book", book);
            dispatcher = request.getRequestDispatcher(PURCHASE_OK_JSP_PAGE);
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher(PURCHASE_FAIL_JSP_PAGE);
            dispatcher.forward(request, response);
        }
    }

    private Book purchase(String name, int numPurchase) {
        if (name == null || name.trim().equals("") ||
                numPurchase <= 0) {
            System.out.println("\nBad arguments");
            return null;
        }

        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        Map<String, Map<String, Object>> result =
                dataBase.query("book",
                        "*",
                        "name=\'" + name + "\'");
        BookHelper helper = new BookHelper();
        List<Book> books = helper.getBookList(result);
        if (books.isEmpty()) {
            System.out.println("\nEmpty result");
            return null;
        }

        Book book = books.get(0);
        if (book.getNumItems() < numPurchase) {
            System.out.println("\nNo enough items");
            return null;
        }

        book.setNumPurchase(numPurchase);
        book.setNumSalesIncrement(numPurchase);
        book.setNumItemsIncrement(-numPurchase);

        dataBase.update("book",
                "numItems,numSales",
                book.getNumItems() + "," + book.getNumSales(),
                "name=\'" + book.getName() + "\'");

        return book;
    }
}
