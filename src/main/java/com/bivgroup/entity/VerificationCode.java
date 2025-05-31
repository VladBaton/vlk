package com.bivgroup.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "VERIFICATIONCODE")
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationCodeId;

    @ManyToOne
    @JoinColumn(name = "insurerId", nullable = false)
    private Insurer insurer;

    private String verificationCode;

    private Date expirationDate;

    @Column(name = "ISUSED")
    private boolean isUsed = false;

    public Long getVerificationCodeId() {
        return verificationCodeId;
    }

    public void setVerificationCodeId(Long verificationCodeId) {
        this.verificationCodeId = verificationCodeId;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }
}
