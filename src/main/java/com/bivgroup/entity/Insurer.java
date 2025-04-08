package com.bivgroup.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "INSURER")
public class Insurer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insurerId;

    private String name;

    private String surname;

    private String lastName;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "insurer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    @OneToOne
    @JoinColumn(name = "ACCOUNTID", referencedColumnName = "ACCOUNTID")
    private Account account;

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
