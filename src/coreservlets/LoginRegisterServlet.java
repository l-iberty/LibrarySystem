package coreservlets;

import coreservlets.util.*;

import java.io.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginRegisterServlet extends HttpServlet {
    private final String REGISTER = "Register";
    private final String LOGIN = "Login";
    private final String CURRENT_PAGE = "/MyPage/LoginRegister.jsp";
    private final String REGISTER_PAGE = "/MyPage/Register.jsp";
    private final String LOGIN_SUCCESS_PAGE = "/books_display_servlet";
    private final String LOGIN_FAIL_PAGE = "/MyPage/LoginFail.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        boolean isValid1 = false, isValid2 = false;
        // name
        String name = request.getParameter("name");
        if (name != null && !name.trim().equals("")) {
            user.setName(name);
            isValid1 = true;
        }
        // passwd
        String passwd = request.getParameter("passwd");
        if (passwd != null && !passwd.trim().equals("")) {
            user.setPasswd(passwd);
            isValid2 = true;
        }
        // type
        int type = UserType.REGULAR_USER;
        try {
            type = Integer.parseInt(request.getParameter("type"));
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        user.setType(type);

        // Does the user choose `Register` or `Login`?
        String opt = request.getParameter("submit");
        RequestDispatcher dispatcher = null;
        DataBase dataBase = new DataBase("root", "2015Liberty", "localhost", "library");
        switch (opt) {
            case REGISTER:
                if (isValid1 && isValid2) {
                    register(dataBase, user);
                    dispatcher = request.getRequestDispatcher(REGISTER_PAGE);
                } else {
                    dispatcher = request.getRequestDispatcher(CURRENT_PAGE);
                }
                break;
            case LOGIN:
                boolean bCheck = checkLogin(dataBase, user);
                if (bCheck) {
                    // session-based bean sharing mechanics
                    HttpSession session = request.getSession();
                    session.setAttribute("userBean", user);
                    dispatcher = request.getRequestDispatcher(LOGIN_SUCCESS_PAGE);
                } else {
                    dispatcher = request.getRequestDispatcher(LOGIN_FAIL_PAGE);
                }
                break;
        }
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    private void register(DataBase dataBase, User user) {
        dataBase.insert("user",
                "(name,passwd,type)",
                "(" +
                        "\'" + user.getName() + "\'," +
                        "\'" + user.getPasswd() + "\'," +
                        "\'" + user.getType() + "\'" +
                        ")");
    }

    private boolean checkLogin(DataBase dataBase, User user) {
        Map<String, Map<String, Object>> result =
                dataBase.query("user", "*",
                        "(" +
                                "name=\'" + user.getName() + "\' and " +
                                "passwd=\'" + user.getPasswd() + "\' and " +
                                "type=" + user.getType() +
                                ")");
        //System.out.println(result.get(user.getName()));
        return result != null && !result.isEmpty();
    }
}
