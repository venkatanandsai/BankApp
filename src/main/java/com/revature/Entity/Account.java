package com.revature.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private int accnt_no;
    private float amt;
    private String username;
    private String type;
    private String cousername;

    public Account(){}

    public Account(int accnt_no, float amt, String username, String type) {
        this.accnt_no = accnt_no;
        this.amt = amt;
        this.username = username;
        this.type = type;
    }

    public Account(int accnt_no, float amt, String username, String type, String cousername) {
        this.accnt_no = accnt_no;
        this.amt = amt;
        this.username = username;
        this.type = type;
        this.cousername = cousername;
    }

    public int getAccnt_no() {
        return accnt_no;
    }

    public void setAccnt_no(int accnt_no) {
        this.accnt_no = accnt_no;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoUsername() {
        return cousername;
    }

    public void setCoUsername(String cousername) {
        this.cousername = cousername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getAccnt_no() == account.getAccnt_no() && Float.compare(getAmt(), account.getAmt()) == 0 && Objects.equals(getUsername(), account.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccnt_no(), getAmt(), getUsername());
    }

    @Override
    public String toString() {
        return "Account{" +
                "accnt_no=" + accnt_no +
                ", amt=" + amt +
                ", username='" + username + '\'' +
                '}';
    }

}
