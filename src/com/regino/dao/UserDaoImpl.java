package com.regino.dao;

import com.regino.domian.User;
import com.regino.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    @Override
    public User login(String username, String password) {
        User user = null;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            // 获取 SQL 预编译执行对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置实际参数
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // 执行 SQL 并返回结果
            resultSet = preparedStatement.executeQuery();
            // 处理结果
            if (resultSet.next()) {
                // 获取 id、用户名、密码
                int id = resultSet.getInt("id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                user = new User(id, username, password);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        boolean isAdded = false;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "insert into user values(?, ?, ?)";
            // 获取 SQL 预编译执行对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置实际参数
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            // 执行 SQL 并返回结果
            int i = preparedStatement.executeUpdate();
            // 处理结果
            if (i > 0) {
                isAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return isAdded;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        User user = null;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "select * from user";
            // 获取 SQL 执行对象（不需要 Statement 的子接口 PreparedStatement 来提升性能和安全性）
            statement = connection.createStatement();
            // 执行 SQL 并返回结果
            resultSet = statement.executeQuery(sql);
            // 处理结果
            while (resultSet.next()) {
                // 获取数据
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(id, username, password);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, statement, connection);
        }
        return list;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where id = ?";
            // 获取 SQL 预编译执行对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置实际参数
            preparedStatement.setString(1, id);
            // 执行 SQL 并返回结果
            resultSet = preparedStatement.executeQuery();
            // 处理结果
            if (resultSet.next()) {
                // 获取数据
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(Integer.valueOf(id), username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean isUpdated = false;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "update user set username = ?, password = ? where id = ?";
            // 获取 SQL 预编译执行对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置实际参数
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            // 执行 SQL 并返回结果
            int i = preparedStatement.executeUpdate();
            // 处理结果
            if (i > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(String id) {
        boolean isDeleted = false;
        try {
            // 从连接池获取连接
            connection = JdbcUtils.getConnection();
            String sql = "delete from user where id = ?";
            // 获取 SQL 预编译执行对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置实际参数
            preparedStatement.setString(1, id);
            // 执行 SQL 并返回结果
            int i = preparedStatement.executeUpdate();
            // 处理结果
            if (i > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return isDeleted;
    }
}
