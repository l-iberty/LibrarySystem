package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PurchaseBookServlet extends HttpServlet {
    private final String PURCHASE_OK_PAGE = "/MyPage/PurchaseOk.jsp";
    private final String PURCHASE_FAIL_PAGE = "/MyPage/PurchaseFail.jsp";

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
        HttpSession session = request.getSession();
        if (book != null) {
            // 当前登录用户
            User user = (User) session.getAttribute("userBean");

            // 添加交易记录
            TradeRecord tradeRecord = new TradeRecord();
            tradeRecord.setUserName(user.getName());
            tradeRecord.setBookName(book.getName());
            tradeRecord.setCost(book.getPrice());
            tradeRecord.setNumBooks(numPurchase);
            addTradeRecord(tradeRecord);

            session.setAttribute("tradeRecordBean", tradeRecord);
            dispatcher = request.getRequestDispatcher(PURCHASE_OK_PAGE);
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher(PURCHASE_FAIL_PAGE);
            dispatcher.forward(request, response);
        }
    }

    // 需在 Test.java 中测试该函数，故声明为 public
    public Book purchase(String name, int numPurchase) {
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
        BookManager bookManager = new BookManager();
        List<Book> books = bookManager.getBookList(result);
        if (books.isEmpty()) {
            System.out.println("\nEmpty result");
            return null;
        }

        Book book = books.get(0);
        if (book.getNumItems() < numPurchase) {
            System.out.println("\nNo enough items");
            return null;
        }

        book.setNumSalesIncrement(numPurchase);
        book.setNumItemsIncrement(-numPurchase);

        dataBase.update("book",
                "numItems,numSales",
                book.getNumItems() + "," + book.getNumSales(),
                "name=\'" + book.getName() + "\'");

        return book;
    }

    // 需在 Test.java 中测试该函数，故声明为 public
    public void addTradeRecord(TradeRecord tradeRecord) {
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        dataBase.insert("trade_record",
                "(userName,bookName,cost,numBooks)",
                "(\'" + tradeRecord.getUserName() + "\'," +
                        "\'" + tradeRecord.getBookName() + "\'," +
                        tradeRecord.getCost() + "," + tradeRecord.getNumBooks() + ")");
    }
}
