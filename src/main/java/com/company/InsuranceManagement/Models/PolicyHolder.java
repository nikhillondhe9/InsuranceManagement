/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 16:13
 */
package com.company.InsuranceManagement.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PolicyHolder {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private String sex;
    private Long AccountNo;
    private String bank;
    private String address;

    public PolicyHolder() {
    }

    public PolicyHolder(String name, String email, String password, String sex, Long accountNo, String bank, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        AccountNo = accountNo;
        this.bank = bank;
        this.address = address;
    }

    public PolicyHolder(Long id, String name, String email, String password, String sex, Long accountNo, String bank, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        AccountNo = accountNo;
        this.bank = bank;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(Long accountNo) {
        AccountNo = accountNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
