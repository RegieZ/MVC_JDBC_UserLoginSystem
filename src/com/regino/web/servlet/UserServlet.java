package com.regino.web.servlet;

import com.regino.domian.User;
import com.regino.service.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    // 请求入口
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 判断请求 action
        String action = request.getParameter("action");

        if (action.equals("login")) {
            this.login(request, response);
        } else if (action.equals("add")) {
            this.add(request, response);
        } else if (action.equals("list")) {
            this.findAll(request, response);
        } else if (action.equals("findById")) {
            this.findById(request, response);
        } else if (action.equals("update")) {
            this.update(request, response);
        } else if (action.equals("delete")) {
            this.delete(request, response);
        }
    }

    // Login
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkStr = request.getParameter("checkStr");
        String checkStrServer = (String) request.getSession().getAttribute("checkImg");
        if (checkStr.equalsIgnoreCase(checkStrServer)) {
            // 调用 service
            User user = userServiceImpl.login(username, password);
            if (user == null) {
                // 登陆失败
                request.setAttribute("msg", "用户名或密码错误！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                // 登陆成功
                request.getSession().setAttribute("loginUser", user);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            request.setAttribute("msg", "验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    // Create
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 接收请求参数 map
            Map<String, String[]> parameterMap = request.getParameterMap();
            // 快速封装 User 实体
            User newUser = new User();
            BeanUtils.populate(newUser, parameterMap);  //如果该实体类没有action数据的时候，beanUtils不会给user封装的。
            // 调用 service 添加 User
            boolean isAdded = userServiceImpl.add(newUser);
            if (isAdded) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
            // 重定向到 Retrieve all
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 调用 service 查询
        List<User> list = userServiceImpl.findAll();
        // 将 List 存储到 Request 域
        request.setAttribute("list", list);
        // 转发到 list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    // Retrieve
    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收请求参数 id
        String id = request.getParameter("id");
        // 调用 service，根据 id 查询
        User user = userServiceImpl.findById(id);
        // 将 user 存储到 request 域
        request.setAttribute("user", user);
        // 转发到 update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    // Update
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 接收请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            // 快速封装到 User 实体
            User newUser = new User();
            BeanUtils.populate(newUser, parameterMap);
            // 调用 service 更新
            boolean isUpdated = userServiceImpl.update(newUser);
            if (isUpdated) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            // 重定向到 Retrieve all
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Delete
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收请求参数 ID
        String id = request.getParameter("id");
        // 调用 service 删除
        boolean isDeleted = userServiceImpl.delete(id);
        if (isDeleted) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        // 重定向到 Retrieve all（再请求转发至 list.jsp）
        response.sendRedirect(request.getContextPath() + "/user?action=list");
    }
}
