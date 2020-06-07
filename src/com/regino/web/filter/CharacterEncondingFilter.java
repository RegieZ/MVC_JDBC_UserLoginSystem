package com.regino.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//该字符串过滤器要解决全局乱码问题
/*
乱码的分类：
     1. 请求乱码(即HTML页面提交表单数据---Servlet通过getParameter()方法获取参数的时候是否乱码)
            get请求：没有
            post请求：有乱码
     2. 响应乱码
            response.getWrite().write("好人"); //向浏览器输出数据乱码
            不管任何请求方式都会乱码的。
 */
//配置过滤路径
@WebFilter("/*")
public class CharacterEncondingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强制类型转换 (目的是为了使用 HttpServletRequest 的 getMethod 方法)
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 解决 Response 响应乱码问题
        response.setContentType("text/html;charset=utf-8");

        //3. 获取客户请求方式，如果是 post 请求方式我们需要解决获取请求参数乱码问题
        String method = request.getMethod();
        if("post".equalsIgnoreCase(method)){
            request.setCharacterEncoding("utf-8");
        }

        //解决完毕乱码之后记得要放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}
