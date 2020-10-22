/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.CommentDTO;
import sample.utilities.DBUtils;

/**
 *
 * @author ASUS
 */
public class CommentDAO {

    private Logger logger = Logger.getLogger(CommentDAO.class.getName());

    public List<CommentDTO> getComments(String id) throws SQLException {
        List<CommentDTO> list = new ArrayList<CommentDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT C.detail, B.name, C.date, C.status, C.articleID, C.commentID, C.email\n"
                    + "FROM Article A, Account B, Comment C\n"
                    + "WHERE C.email = B.email AND A.articleID = C.articleID\n"
                    + "AND C.articleID = ? AND C.status = 'S005' ORDER BY C.date DESC";
            stm = conn.prepareStatement(sql);

            stm.setString(1, id);

            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String detail = rs.getString("detail");
                Date date = rs.getDate("date");
                String commentID = rs.getString("commentID");
                String articleID = rs.getString("articleID");
                String status = rs.getString("status");
                String email = rs.getString("email");

                list.add(new CommentDTO(commentID, articleID, detail, name, status, email, date));
            }

        } catch (Exception e) {
            logger.error("Error in CommentDAO-getComments: " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public void addComment(CommentDTO comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO Comment(commentID, articleID, email, detail, date, status) VALUES(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, comment.getCommentID());
            stm.setString(2, comment.getArticleID());
            stm.setString(3, comment.getEmail());
            stm.setString(4, comment.getDetail());
            stm.setDate(5, comment.getDate());
            stm.setString(6, comment.getStatus());

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in CommentDAO-addComment: " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteComment(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Comment SET status = 'S006' WHERE commentID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in CommentDAO-deleteComment: " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
