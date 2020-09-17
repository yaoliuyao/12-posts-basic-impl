package com.nfit.yaoliusan.myblog.dao;

import com.nfit.yaoliusan.myblog.bean.Comment;
import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class CommentDAO {
    /**
     * 获取所有评论
     *
     * @return
     * @throws Exception
     */
    public List<Comment> getCommentById(int id) throws Exception {
        String sql = "select row_number( ) over(partition by postid order by id) row, id, content, author, created from comment where postid  = ? order by created desc";
        QueryRunner run = new QueryRunner();
        Connection conn = DBHelper.getConnection();
        List<Comment> commentList;
        try {
            commentList = run.query(
                    conn, sql, new BeanListHandler<Comment>(Comment.class), id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return commentList;
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    public int addComment(Comment comment) throws Exception {
        String sql = "insert into comment(postid , author, content) values(?, ?, ?)";
        QueryRunner run = new QueryRunner();
        Connection conn = DBHelper.getConnection();
        Object[] objects = {
                comment.getPost().getId(), comment.getAuthor(), comment.getContent()
        };
        int count = run.update(conn, sql, objects);
        DbUtils.closeQuietly(conn);
        return count;
    }
}
