package coreservlets;

import coreservlets.util.*;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        User user = new User();
        Book book = new Book();
        LoginRegisterServlet loginRegisterServlet = new LoginRegisterServlet();
        PurchaseBookServlet purchaseBookServlet = new PurchaseBookServlet();

        /**
         * 测试编号: TestCase-FUNC-01
         * 测试内容: 用户注册
         */
        user.setName("User1");
        user.setPasswd("123");
        user.setType(UserType.REGULAR_USER);
        loginRegisterServlet.register(dataBase, user);

        /**
         * 测试编号: TestCase-FUNC-02
         * 测试内容: 用户登录
         */
        if (loginRegisterServlet.checkLogin(dataBase, user)) {
            System.out.println("Login Succeeded!");
        } else {
            System.out.println("Login Failed!");
        }

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 用户购买图书
         */
        // 合法输入
        book = purchaseBookServlet.purchase("Thinking in Java", 5);
        if (book != null) {
            purchaseBookServlet.addTradeRecord(user, book);
        }
        // 非法输入
        /* 1 */
        book = purchaseBookServlet.purchase("Thinking in Java", 0);
        if (book != null) {
            purchaseBookServlet.addTradeRecord(user, book);
        }
        /* 2 */
        book = purchaseBookServlet.purchase("Thinking in Java", -5);
        if (book != null) {
            purchaseBookServlet.addTradeRecord(user, book);
        }

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 用户输入图书ID查看图书详细信息
         */
        Map<String, Map<String, Object>> result = dataBase.query("book", "*", "id=\'B1234\'");
        BookManager manager = new BookManager();
        List<Book> books = manager.getBookList(result);
        String s = manager.getDetailedInfo(books);
        System.out.println(s);

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 管理员添加图书
         */
        dataBase.insert("book",
                "(id,name,author,price,numItems,numSales)",
                "('W1234','Book_1','Author_1',79,300,0)");
        System.out.println("Book add OK");

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 管理员下架图书
         */
        if (!dataBase.query("book", "*", "id='A1234'").isEmpty()) {
            dataBase.delete("book", "id='A1234'");
            System.out.println("Book delete OK");
        } else {
            System.out.println("Book Not Found!");
        }
    }
}
