package com.bivgroup.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class ContractExtension extends PanacheEntityBase {

    @Id
    private Long contractId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Contract contract;

    private Long longField01;

    private Long longField02;

    private Long longField03;

    private Long longField04;

    private Long longField05;

    private Double doubleField01;

    private Double doubleField02;

    private Double doubleField03;

    private Double doubleField04;

    private Double doubleField05;

    private String stringField01;

    private String stringField02;

    private String stringField03;

    private String stringField04;

    private String stringField05;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getLongField01() {
        return longField01;
    }

    public void setLongField01(Long longField01) {
        this.longField01 = longField01;
    }

    public Long getLongField02() {
        return longField02;
    }

    public void setLongField02(Long longField02) {
        this.longField02 = longField02;
    }

    public Long getLongField03() {
        return longField03;
    }

    public void setLongField03(Long longField03) {
        this.longField03 = longField03;
    }

    public Long getLongField04() {
        return longField04;
    }

    public void setLongField04(Long longField04) {
        this.longField04 = longField04;
    }

    public Long getLongField05() {
        return longField05;
    }

    public void setLongField05(Long longField05) {
        this.longField05 = longField05;
    }

    public Double getDoubleField01() {
        return doubleField01;
    }

    public void setDoubleField01(Double doubleField01) {
        this.doubleField01 = doubleField01;
    }

    public Double getDoubleField02() {
        return doubleField02;
    }

    public void setDoubleField02(Double doubleField02) {
        this.doubleField02 = doubleField02;
    }

    public Double getDoubleField03() {
        return doubleField03;
    }

    public void setDoubleField03(Double doubleField03) {
        this.doubleField03 = doubleField03;
    }

    public Double getDoubleField04() {
        return doubleField04;
    }

    public void setDoubleField04(Double doubleField04) {
        this.doubleField04 = doubleField04;
    }

    public Double getDoubleField05() {
        return doubleField05;
    }

    public void setDoubleField05(Double doubleField05) {
        this.doubleField05 = doubleField05;
    }

    public String getStringField01() {
        return stringField01;
    }

    public void setStringField01(String stringField01) {
        this.stringField01 = stringField01;
    }

    public String getStringField02() {
        return stringField02;
    }

    public void setStringField02(String stringField02) {
        this.stringField02 = stringField02;
    }

    public String getStringField03() {
        return stringField03;
    }

    public void setStringField03(String stringField03) {
        this.stringField03 = stringField03;
    }

    public String getStringField04() {
        return stringField04;
    }

    public void setStringField04(String stringField04) {
        this.stringField04 = stringField04;
    }

    public String getStringField05() {
        return stringField05;
    }

    public void setStringField05(String stringField05) {
        this.stringField05 = stringField05;
    }
}
