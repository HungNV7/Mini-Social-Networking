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
import sample.utilities.DBUtils;
import org.apache.log4j.Logger;
import sample.dtos.NotificationDTO;

/**
 *
 * @author ASUS
 */
public class NotificationDAO {

    private Logger logger = Logger.getLogger(NotificationDAO.class.getName());

    public void addNotification(NotificationDTO notification) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO Notification(notifyID, articleID, email, date, type, status, isRead) VALUES(?,?,?,?,?,?,?)";

            stm = conn.prepareStatement(sql);
            stm.setString(1, notification.getId());
            stm.setString(2, notification.getArticleID());
            stm.setString(3, notification.getEmail());
            stm.setDate(4, notification.getDate());
            stm.setString(5, notification.getType());
            stm.setString(6, notification.getStatus());
            stm.setBoolean(7, notification.isIsRead());

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-addNotification: " + e);
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

    public void updateNotification(NotificationDTO notification) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Notification SET status = ?, type = ?, isRead = ?, date = ? WHERE notifyID = ?";

            stm = conn.prepareStatement(sql);
            stm.setString(1, notification.getStatus());
            stm.setString(2, notification.getType());
            stm.setBoolean(3, notification.isIsRead());
            stm.setDate(4, notification.getDate());
            stm.setString(5, notification.getId());

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-updateNotification: " + e);
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
    
        public void updateNotification(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Notification SET isRead = 1 WHERE notifyID = ?";

            stm = conn.prepareStatement(sql);
            stm.setString(1, id);

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-updateNotification: " + e);
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

    public NotificationDTO getNotification(String articleID, String email, String type) throws SQLException {
        NotificationDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT notifyID, date, status, isRead FROM Notification WHERE articleID = ? AND email = ? AND type = ?";

            stm = conn.prepareStatement(sql);
            stm.setString(1, articleID);
            stm.setString(2, email);
            stm.setString(3, type);
            rs = stm.executeQuery();

            if (rs.next()) {
                String notifyID = rs.getString("notifyID");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                boolean isRead = rs.getBoolean("isRead");

                result = new NotificationDTO(notifyID, articleID, email, type, status, date, isRead);
            }

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-getNotification: " + e);
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
    
   

    public List<NotificationDTO> getListNotification(String email) throws SQLException {
        List<NotificationDTO> list = new ArrayList<NotificationDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT notifyID, C.email, C.date, C.status, C.articleID, C.isRead, A.name, type, B.image\n"
                    + "FROM Account A, Article B, Notification C\n"
                    + "WHERE A.email = C.email  AND B.articleID = C.articleID AND C.status = 'S007'\n"
                    + "AND B.email = ? ORDER BY C.date DESC";

            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();

            while (rs.next()) {
                String notifyID = rs.getString("notifyID");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                boolean isRead = rs.getBoolean("isRead");
                String type =  rs.getString("type");
                String name = rs.getString("name");
                String gmail = rs.getString("email");
                String articleID = rs.getString("articleID");
                String image = rs.getString("image");

                list.add(new NotificationDTO(notifyID, articleID, email, type, status, name, image, date, isRead));
            }

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-getListNotification: " + e);
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
    
    public void deleteNotification(String articleID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Notification SET status = 'S008' WHERE articleID = ?";

            stm = conn.prepareStatement(sql);
            stm.setString(1, articleID);
           
            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in NotificationDAO-deleteNotification: " + e);
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
