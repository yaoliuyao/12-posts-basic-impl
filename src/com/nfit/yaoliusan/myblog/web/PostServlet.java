package com.nfit.yaoliusan.myblog.web;


import com.nfit.yaoliusan.myblog.bean.Comment;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.CommentDAO;
import com.nfit.yaoliusan.myblog.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = req.getParameter("id") == null ? (int) ((Post) session.getAttribute("post")).getId() : Integer.parseInt(req.getParameter("id"));
        PostDAO dao = new PostDAO();
        CommentDAO dao1 = new CommentDAO();
        try {
            Post post = dao.getPostById(id);
            session.setAttribute("post", post);
            List<Comment> commentList = dao1.getCommentById(post.getId());
            req.setAttribute("comments", commentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/jsp/post.jsp").forward(req, resp);
    }
}
