
package com.virus.entity;

// @author krodriguez

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "UserRole",uniqueConstraints = @UniqueConstraint(
        columnNames = {"role", "username"}))
public class UserRole {
    @Id
    @GeneratedValue
    @Column (name = "userroleid",unique = true, nullable = false)
    private Integer userRoleId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username",nullable = false)
    private Userr user;
    
    @Column(name = "role", nullable = false,length = 45)
    private String role;

    public UserRole(Userr user, String role) {
        super();
        this.user = user;
        this.role = role;
    }
    
    public UserRole(){
        
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Userr getUser() {
        return user;
    }

    public void setUser(Userr user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
}
