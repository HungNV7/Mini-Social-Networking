/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ArticleDTO {
    private String id, title, image, status, accountName, email;
    private List<String> description;
    private Date date;

    public ArticleDTO() {
        description = new ArrayList<String>();
    }

    public ArticleDTO(String id, String title, String image, String status, String accountName, String email, List<String> description, Date date) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.status = status;
        this.accountName = accountName;
        this.email = email;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
