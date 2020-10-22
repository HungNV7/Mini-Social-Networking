/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author ASUS
 */
public class ErrorAccountDTO {
    private String errorEmail, errorName, errorPassword, errorRePassword;

    public ErrorAccountDTO() {
    }

    public ErrorAccountDTO(String errorEmail, String errorName, String errorPassword, String errorRePassword) {
        this.errorEmail = errorEmail;
        this.errorName = errorName;
        this.errorPassword = errorPassword;
        this.errorRePassword = errorRePassword;
    }

    public String getErrorEmail() {
        return errorEmail;
    }

    public void setErrorEmail(String errorEmail) {
        this.errorEmail = errorEmail;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

    public String getErrorRePassword() {
        return errorRePassword;
    }

    public void setErrorRePassword(String errorRePassword) {
        this.errorRePassword = errorRePassword;
    }
    
    
        
}
