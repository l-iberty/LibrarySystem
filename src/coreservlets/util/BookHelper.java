package coreservlets.util;

import coreservlets.*;

import javax.servlet.http.HttpSession;
import java.util.*;

public interface BookHelper {
    public List<Book> getBookList(Map<String, Map<String, Object>> result);

    public String getBasicInfo(List<Book> books);

    public String getDetailedInfo(List<Book> books);

    public void informDisplay(HttpSession session, DataBase dataBase);
}
