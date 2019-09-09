/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 13:17
 */
package com.company.InsuranceManagement.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private int age;
    private Long accountNo;
    private String bank;

    public Agent() {
    }

    public Agent(Long id, String name, String email, String password, int age, Long accountNo, String bank) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.accountNo = accountNo;
        this.bank = bank;
    }

    public Agent(String name, String email, String password, int age, Long accountNo, String bank) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.accountNo = accountNo;
        this.bank = bank;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
