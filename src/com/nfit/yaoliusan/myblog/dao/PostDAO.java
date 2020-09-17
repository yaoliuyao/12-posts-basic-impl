package com.nfit.yaoliusan.myblog.dao;

import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class PostDAO {

    /**
     * 获取所有文章
     *
     * @return
     * @throws Exception
     */
    public List<Post> getAll() throws Exception {
        String sql = "select id, title, content, author, created from post order by created Desc";
        QueryRunner run = new QueryRunner();
        Connection conn = DBHelper.getConnection();
        List<Post> postList;
        try {
            postList = run.query(
                    conn, sql, new BeanListHandler<Post>(Post.class));
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return postList;
    }

    /**
     * 根据PostId获取文章
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Post getPostById(int id) throws Exception {
        String sql = "select id, title, content, author, created from post where id = ?";
        QueryRunner run = new QueryRunner();
        Connection conn = DBHelper.getConnection();
        Post post;
        try {
            post = run.query(
                    conn, sql, new BeanHandler<Post>(Post.class), id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return post;
    }

    /**
     * 添加文章
     *
     * @param post
     * @return
     */
    public int addPost(Post post) throws Exception {
        String sql = "insert into post(title, content, author) values (?, ?, ?)";
        Connection conn = DBHelper.getConnection();
        QueryRunner run = new QueryRunner();
        Object[] objects = {
                post.getTitle(), post.getContent(), post.getAuthor()
        };
        int count = run.update(conn, sql, objects);
        DbUtils.closeQuietly(conn);
        return  count;
    }

}
