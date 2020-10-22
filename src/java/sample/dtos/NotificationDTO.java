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
public class NotificationDTO {
    private String id, articleID, email, type, status, name, image;
    private Date date;
    private boolean isRead;

    public NotificationDTO() {
    }

    public NotificationDTO(String id, String articleID, String email, String type, String status, Date date, boolean isRead) {
        this.id = id;
        this.articleID = articleID;
        this.email = email;
        this.type = type;
        this.status = status;
        this.date = date;
        this.isRead = isRead;
    }

    public NotificationDTO(String id, String articleID, String email, String type, String status, String name, String image, Date date, boolean isRead) {
        this.id = id;
        this.articleID = articleID;
        this.email = email;
        this.type = type;
        this.status = status;
        this.name = name;
        this.image = image;
        this.date = date;
        this.isRead = isRead;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
