package com.regino.service;

import com.regino.domian.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class allTest {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    @Test
    public void test01() throws Exception {
        System.out.println(new UserServiceImpl().add(new User(3, "add", "addPW")));
    }

    @Test
    public void test02() throws Exception {
        System.out.println(new UserServiceImpl().login("add", "addPW"));
    }

    @Test
    public void test03() throws Exception {
        System.out.println(new UserServiceImpl().findAll());
    }

    @Test
    public void test04() throws Exception {
        System.out.println(new UserServiceImpl().findById("1"));
    }

    @Test
    public void test05() throws Exception {
        System.out.println(new UserServiceImpl().update(new User(1, "add", "addPW")));
    }

    @Test
    public void test06() throws Exception {
        System.out.println(new UserServiceImpl().delete("1"));
    }
}
