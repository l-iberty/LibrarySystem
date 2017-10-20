package coreservlets;

import coreservlets.util.UserType;

public class User {
    private String name = "";
    private String passwd = "";
    private int type = UserType.REGULAR_USER;

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setType(int type) {
        this.type = type;
    }
}
