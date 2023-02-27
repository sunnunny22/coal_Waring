package com.servlet;

import com.Service.userService;
import com.view_object.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/login.do")
public class LoginDoServlet extends HttpServlet {
    private final userService userService = new userService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 读取用户名 + 密码
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 进行登录验证（验证用户名 + 密码是否正确）
        UserVO userVO = userService.login(username, password);

        // 为后边的响应做好准备
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();

        if (userVO == null) {
            // 登录失败
            // 响应 “登录失败"
            writer.println("登录失败");
            return;
        }

        // 3. 将用户对象放入 session 中
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", userVO);

        // 4. 响应”登录成功“
        writer.println("登录成功");
        resp.sendRedirect("/html/registe_success.html");
    }
}
