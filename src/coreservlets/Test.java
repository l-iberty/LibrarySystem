package coreservlets;

import coreservlets.util.*;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        User user = new User();
        Book book = new Book();
        TradeRecord tradeRecord = new TradeRecord();
        LoginRegisterServlet loginRegisterServlet = new LoginRegisterServlet();
        PurchaseBookServlet purchaseBookServlet = new PurchaseBookServlet();

        /** 以下测试的成功与否还需要查询数据库进行判断 */

        /**
         * 测试编号: TestCase-FUNC-01
         * 测试内容: 用户注册
         */
        // 普通用户注册
        user.setName("User1");
        user.setPasswd("123");
        user.setType(UserType.REGULAR_USER);
        loginRegisterServlet.register(dataBase, user);
        // 管理员用户注册
        User admin=new User();
        admin.setName("admin");
        admin.setPasswd("admin");
        admin.setType(UserType.ADMINISTRATOR);
        loginRegisterServlet.register(dataBase,admin);
        // 查询 user 表，若存在相关记录则说明测试通过
        System.out.println("[TestCase-FUNC-01] is done! Please check database");

        /**
         * 测试编号: TestCase-FUNC-02
         * 测试内容: 用户登录
         */
        // 正确输入
        if (loginRegisterServlet.checkLogin(dataBase, user)) {
            System.out.println("[TestCase-FUNC-02] passed!");
        } else {
            System.out.println("[TestCase-FUNC-02] failed!");
        }
        // 用户名和密码仅一个正确；使用未注册帐号登录
        // 相关测试已在使用过程中完成，不再写专门的测试代码


        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 用户购买图书
         */
        // 正确输入
        int numBooks = 5;
        book = purchaseBookServlet.purchase("Thinking in Java", numBooks);
        if (book != null) {
            tradeRecord.setUserName(user.getName());
            tradeRecord.setBookName(book.getName());
            tradeRecord.setCost(book.getPrice());
            tradeRecord.setNumBooks(numBooks);
            purchaseBookServlet.addTradeRecord(tradeRecord);
            System.out.println("[TestCase-FUNC-xx] passed!");
        } else {
            System.out.println("[TestCase-FUNC-xx] failed!");
        }
        // 查询 trade_record 表，检查交易记录是否存在; 另需查看 book 表，
        // 检查 库存 和 销量 是否发生变化.
        // 其他情况: 购买的书不存在；购买数量不合法(小于或等于0,大于库存)
        // 相关测试已在使用过程中完成，不再写专门的测试代码

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 用户输入图书ID查看图书详细信息
         */
        // 正确输入
        Map<String, Map<String, Object>> result = dataBase.query("book", "*", "id='B1234'");
        if (!result.isEmpty()) {
            BookManager manager = new BookManager();
            List<Book> books = manager.getBookList(result);
            String s = manager.getDetailedInfo(books);
            System.out.println(s);
            System.out.println("[TestCase-FUNC-xx] passed!");
        } else {
            System.out.println("[TestCase-FUNC-xx] failed!");
        }
        // ID不正确
        result = dataBase.query("book", "*", "id='00000'");
        if (result.isEmpty()) {
            System.out.println("[TestCase-FUNC-xx] passed!");
        } else {
            System.out.println("[TestCase-FUNC-xx] failed!");
        }

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 管理员添加图书
         */
        dataBase.insert("book",
                "(id,name,author,price,numItems,numSales)",
                "('W1234','Book_1','Author_1',79,300,0)");
        System.out.println("[TestCase-FUNC-xx] is done! Please check database");
        // 若 book 表中出现相关记录，则测试通过

        /**
         * 测试编号: TestCase-FUNC-xx
         * 测试内容: 管理员下架图书
         */
        // 正确输入
        if (!dataBase.query("book", "*", "id='A1234'").isEmpty()) {
            dataBase.delete("book", "id='A1234'");
            System.out.println("[TestCase-FUNC-xx] passed!");
        } else {
            System.out.println("[TestCase-FUNC-xx] failed!");
        }
        // ID错误
        if (!dataBase.query("book", "*", "id='00000'").isEmpty()) {
            System.out.println("[TestCase-FUNC-xx] failed!");
        } else {
            System.out.println("[TestCase-FUNC-xx] passed!");
        }
        // 若相关记录已从 book 表消失，则说明测试通过.
    }
}
