package com.databasecourse.salary.entities;

import javax.persistence.*;

/**
 * date:2016-05-25 14:27
 */
@Entity
@Table(name = "admin", schema = "")

public class Admin {
    private Integer id;
    private String name;
    private String pass;
    private String image;
    private Integer auth;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.name = username;
        this.pass = password;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "auth")
    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        if (name != null ? !name.equals(admin.name) : admin.name != null) return false;
        if (pass != null ? !pass.equals(admin.pass) : admin.pass != null) return false;
        if (image != null ? !image.equals(admin.image) : admin.image != null) return false;
        if (auth != null ? !auth.equals(admin.auth) : admin.auth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        return result;
    }
}
