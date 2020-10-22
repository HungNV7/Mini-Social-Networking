/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utilities.DBUtils;
import org.apache.log4j.Logger;
import sample.dtos.AccountDTO;

/**
 *
 * @author ASUS
 */
//nhập tên có thể có chữ có dấu
public class AccountDAO {
    private Logger log = Logger.getLogger(AccountDAO.class);

    public AccountDTO checkEmail(String email) throws SQLException {
        AccountDTO account = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "SELECT name, role, status FROM Account WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);

            rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("role");
                String status = rs.getString("status");
                
                account = new AccountDTO(email, name, "", role, status);
            }

        } catch (Exception e) {
            log.error("Error in AccountDAO-checkEmail: " + e);
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
        return account;
    }

    public void addAccount(AccountDTO account, int code) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "INSERT INTO Account(email, name, password, role, status, code) VALUES(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, account.getEmail());
            stm.setString(2, account.getName());
            stm.setString(3, account.getPassword());
            stm.setString(4, account.getRole());
            stm.setString(5, account.getStatus());
            stm.setString(6, ""+code);

            stm.executeUpdate();

        } catch (Exception e) {
            log.error("Error in AccountDAO-addAccount: " + e);
            System.out.println("Error in AccountDAO_addAccount: " + e);
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

    public AccountDTO checkLogin(String email, String password) throws SQLException {
        AccountDTO account = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "SELECT name, status, role FROM Account WHERE email=? AND password=? AND status = 'S002'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);

            rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("role");
                String status = rs.getString("status");

                account = new AccountDTO(email, name, "", role, status);
            }

        } catch (Exception e) {
            log.error("Error in AccountDAO-checkLogin: " + e);
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
        return account;
    }

    public boolean checkCode(String email, String code) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "SELECT name FROM Account WHERE email=? AND code = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, code);

            rs = stm.executeQuery();
            if (rs.next()) {
                result = true;
            }

        } catch (Exception e) {
            log.error("Error in AccountDAO-checkCode: " + e);
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

    public void updateStatus(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "UPDATE Account SET status = 'S002' WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);

            stm.executeUpdate();

        } catch (Exception e) {
            log.error("Error in AccountDAO-updateStatus: " + e);
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
    
    public void updateAccount(AccountDTO account, int code) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            String sql = "UPDATE Account SET status = ?, password = ?, name = ?, code = ? WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, account.getStatus());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getName());
            stm.setString(4, code + "");
            stm.setString(5, account.getEmail());

            stm.executeUpdate();

        } catch (Exception e) {
            log.error("Error in AccountDAO-updateStatus: " + e);
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
