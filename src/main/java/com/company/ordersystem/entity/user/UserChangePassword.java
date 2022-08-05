package com.company.ordersystem.entity.user;

public class UserChangePassword {

    private String currentPassword;
    private String newPassword;
    private String newPassword2;

    public UserChangePassword() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = "{noop}" +currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = "{noop}" + newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = "{noop}" +newPassword2;
    }

    public boolean isEqualNewPassword (String currentPassword){
        return currentPassword.equals(this.currentPassword) && newPassword.equals(newPassword2);
    }
}
