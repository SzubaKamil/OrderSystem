package com.company.ordersystem.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "outlook_setting")
public class OutlookSetting {


    @Id
    @Column(name = "username", unique=true, columnDefinition="VARCHAR(50)")
    private String username;

    @Column(name = "cc_emails")
    private String ccEmails;

    @Column(name = "file_path")
    private String filePath;

    @Column (name = "signature")
    private String signature;

    @JoinColumn(name = "username")
    @OneToOne
    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(String ccEmails) {
        this.ccEmails = ccEmails;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void   setSettings (OutlookSetting outlookSetting){
        this.ccEmails = outlookSetting.getCcEmails();
        this.filePath = outlookSetting.getFilePath();
        this.signature = outlookSetting.getSignature();
    }

    @Override
    public String toString() {
        return "OutlookSetting{" +
                "username='" + username + '\'' +
                ", ccEmails='" + ccEmails + '\'' +
                ", filePath='" + filePath + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
