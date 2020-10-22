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
import org.apache.log4j.Logger;
import sample.dtos.EmotionDTO;
import sample.utilities.DBUtils;

/**
 *
 * @author ASUS
 */
public class EmotionDAO {

    private Logger logger = Logger.getLogger(EmotionDAO.class.getName());

    public int getEmotions(String icon, String articleID) throws SQLException {
        int number = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT COUNT(emotionID) AS total FROM Emotion WHERE icon = ? AND articleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, icon);
            stm.setString(2, articleID);

            rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("total");
            }

        } catch (Exception e) {
            logger.error("Error in EmotionDAO-getEmotions: " + e);
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
        return number;
    }

    public EmotionDTO getOwnEmotion(String articleID, String email) throws SQLException {
        EmotionDTO emotion = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT emotionID, date, icon FROM Emotion WHERE articleID = ? AND email = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, articleID);
            stm.setString(2, email);

            rs = stm.executeQuery();
            if (rs.next()) {
                String emotionID = rs.getString("emotionID");
                Date date = rs.getDate("date");
                String icon = rs.getString("icon").trim();
                emotion = new EmotionDTO(emotionID, articleID, email, icon, date);
            }

        } catch (Exception e) {
            logger.error("Error in EmotionDAO-getOwnEmotion: " + e);
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
        return emotion;
    }

    public void insertEmotion(EmotionDTO emotion) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO Emotion(emotionID, articleID, email, date, icon) VALUES(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, emotion.getEmotionID());
            stm.setString(2, emotion.getArticleID());
            stm.setString(3, emotion.getEmail());
            stm.setDate(4, emotion.getDate());
            stm.setString(5, emotion.getIcon());

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in EmotionDAO-insertEmotion: " + e);
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

    public void updateEmotion(EmotionDTO emotion) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Emotion SET icon = ? WHERE emotionID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(2, emotion.getEmotionID());
            stm.setString(1, emotion.getIcon());

            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in EmotionDAO-updateEmotion: " + e);
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

    public void deleteEmotion(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM Emotion WHERE articleID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();

        } catch (Exception e) {
            logger.error("Error in CommentDAO-deleteEmotion: " + e);
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
