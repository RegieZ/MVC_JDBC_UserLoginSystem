package com.regino.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckServlet", urlPatterns = "/checkServlet")
public class CheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        int width = 120;
        int height = 40;
        //首先创建一个画布
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //创建当前画布的画笔
        Graphics gh = bufferedImage.getGraphics();

        //设置背景色颜色
        gh.setColor(Color.white);
        //绘画 背景
        gh.fillRect(0, 0, width, height);

        //设置边框的颜色
        gh.setColor(Color.red);
        //绘画边框
        gh.drawRect(0, 0, width - 1, height - 1);

        //定义一个random
        Random rd = new Random();
        //在画布上绘画验证码的内容
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //定义一个变量保存验证码
        String checkStr = "";
        for (int i = 1; i < 5; i++) {

            //设置字体
            Font font = new Font("宋体", Font.PLAIN, 28);
            gh.setFont(font);
            //设置字体的颜色
            gh.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
            //获取需要绘画的文字
            String s = str.charAt(rd.nextInt(str.length())) + "";
            //将字体画到画布上去
            gh.drawString(s, 10 + (i - 1) * 30, 30);
            checkStr += s;
        }

        //绘画障碍物
        for (int i = 1; i < 10; i++) {
            //设置线条的颜色
            gh.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
            //绘画线条
            gh.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
        }
        //将图片保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("checkImg", checkStr);
        //将图片输出到前端
        /*
         * 第一个参数：需要输出的画布
         * 第二个参数：格式
         * 第三个参数：输出流
         */
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }
}