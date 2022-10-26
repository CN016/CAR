package com.designby016.car.object;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;
@TableName("user")
public class User {
    private String username;
    private String password;
    private String tel;

    public User() {
    }

    public User(String username, String password, String tel) {
        this.username = username;
        this.password = password;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(tel, user.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, tel);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
