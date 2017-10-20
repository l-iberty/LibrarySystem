package coreservlets.util;

import coreservlets.Book;

import java.util.*;

public class BookHelper {
    public List<Book> getBookList(Map<String, Map<String, Object>> result) {
        List<Book> books = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : result.entrySet()) {
            Map<String, Object> value = entry.getValue();

            String id = (String) value.get("id");
            String name = (String) value.get("name");
            String author = (String) value.get("author");
            double price = 0.0;
            int numItems = 0, numSales = 0;
            try {
                price = Double.parseDouble((String) value.get("price"));
                numItems = Integer.parseInt((String) value.get("numItems"));
                numSales = Integer.parseInt((String) value.get("numSales"));
            } catch (NumberFormatException nfe) {
                // do nothing
            }

            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setPrice(price);
            book.setNumItems(numItems);
            book.setNumSales(numSales);
            books.add(book);
        }
        return books;
    }

    public String getBasicInfo(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        for (Book book : books) {
            String s = "<hr><b>ID:</b> " + book.getId() +
                    "<br><b>Name:</b> " + book.getName();
            builder.append(s);
        }
        return builder.toString();
    }

    public String getDetailedInfo(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        for (Book book : books) {
            String s = "<hr><b>ID:</b> " + book.getId() +
                    "<br><b>Name:</b> " + book.getName() +
                    "<br><b>Author:</b> " + book.getAuthor() +
                    "<br><b>Price:</b> " + book.getPrice() +
                    "<br><b>Num Items:</b> " + book.getNumItems() +
                    "<br><b>Num Sales:</b> " + book.getNumSales();
            builder.append(s);
        }
        return builder.toString();
    }
}
