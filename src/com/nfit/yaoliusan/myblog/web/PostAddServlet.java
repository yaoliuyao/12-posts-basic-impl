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
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try {
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.addPost(new Post(title, content, author));
            // 跳转到详情页面更合理
            resp.sendRedirect(req.getContextPath() + "/post?id=" + post.getId());
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getLocalizedMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
