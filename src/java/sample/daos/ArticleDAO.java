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
import sample.dtos.ArticleDTO;
import sample.utilities.DBUtils;

import org.apache.log4j.Logger;

/**
 *
 * @author ASUS
 */
public class ArticleDAO {

    private Logger logger = Logger.getLogger(ArticleDAO.class.getName());

    public List<ArticleDTO> getListArticle(String search, int begin) throws SQLException {
        List<ArticleDTO> list = new ArrayList<ArticleDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT articleID, title, description, image, A.date, A.status, name, B.email\n"
                    + "FROM Article A, Account B \n"
                    + "WHERE A.status = 'S004' AND A.email = B.email AND description LIKE ?\n"
                    + "ORDER BY date DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            stm = conn.prepareStatement(sql);

            stm.setString(1, "%" + search + "%");
            stm.setInt(2, begin);

            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("articleID");
                String name = rs.getString("name");
                String title = rs.getString("title");
                String[] tmp = rs.getString("description").split("[\\n]");
                List<String> description = new ArrayList<String>();
                for (String s : tmp) {
                    description.add(s);
                }

                String image = rs.getString("image");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                String email = rs.getString("email");

                list.add(new ArticleDTO(id, title, image, status, name, email, description, date));
            }
        } catch (Exception e) {
            logger.error("Error in ArticleDAO-getListArticle: " + e);
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

    public int getCount(String search) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT COUNT(articleID) AS total \n"
                    + "FROM Article \n"
                    + "WHERE status = 'S004' AND description LIKE ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");

            rs = stm.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString("total"));
            }
        } catch (Exception e) {
            logger.error("Error in ArticleDAO-getCount: " + e);
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
        return result;
    }

    public ArticleDTO getArticle(String id) throws SQLException {
        ArticleDTO article = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT title, description, image, date, B.status, name, B.email\n"
                    + "FROM Account A, Article B\n"
                    + "WHERE A.email = B.email AND B.status = 'S004' AND articleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String title = rs.getString("title");
                String[] tmp = rs.getString("description").split("[\\n]");
                List<String> description = new ArrayList<String>();
                for (String s : tmp) {
                    description.add(s);
                }

                String image = rs.getString("image");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                String email = rs.getString("email");

                article = new ArticleDTO(id, title, image, status, name, email, description, date);
            }

        } catch (Exception e) {
            logger.error("Error in ArticleDAO-getArticle: " + e);
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
        return article;
    }

    public void addArticle(ArticleDTO article) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO Article(articleID, title, description, image, email, status, date)\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, article.getId());
            stm.setString(2, article.getTitle());
            stm.setString(3, article.getDescription().get(0));
            stm.setString(4, article.getImage());
            stm.setString(5, article.getEmail());
            stm.setString(6, article.getStatus());
            stm.setDate(7, article.getDate());
            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in ArticleDAO-addArticle: " + e);
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
    
    public void deleteArticle(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Article SET status = 'S003' WHERE articleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            
            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in ArticleDAO-deleteArticle: " + e);
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
