package coreservlets;

/**
 * 交易记录
 */
public class TradeRecord {
    private String userName = "";
    private String bookName = "";
    private double cost = 0.0; // 单价
    private int numBooks = 0; // 购买数量

    public String getUserName() {
        return userName;
    }

    public String getBookName() {
        return bookName;
    }

    public double getCost() {
        return cost;
    }

    public int getNumBooks() {
        return numBooks;
    }

    // 总价
    public double getTotalCost() {
        return cost * numBooks;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }
}
