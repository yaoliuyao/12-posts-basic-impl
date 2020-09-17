package com.nfit.yaoliusan.myblog.web;

import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/add")
public class PostAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDAO dao = new PostDAO();
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try {
            dao.addPost(new Post(title, content, author));
            resp.sendRedirect(req.getContextPath() + "/posts");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
