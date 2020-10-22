


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class EmotionDTO {
    private String emotionID, articleID, email, icon;
    private Date date;

    public EmotionDTO() {
    }

    public EmotionDTO(String emotionID, String articleID, String email, String icon, Date date) {
        this.emotionID = emotionID;
        this.articleID = articleID;
        this.email = email;
        this.icon = icon;
        this.date = date;
    }

    public String getEmotionID() {
        return emotionID;
    }

    public void setEmotionID(String emotionID) {
        this.emotionID = emotionID;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
