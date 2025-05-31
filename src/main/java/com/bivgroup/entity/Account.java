package com.bivgroup.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID")
    private Long accountId;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Insurer insurer;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
