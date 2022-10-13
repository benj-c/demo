package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "demo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    @Basic
    @Column(name = "ENABLED", nullable = false, columnDefinition = "TINYINT")
    private boolean enabled;

    @Basic
    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && enabled == that.enabled && Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, enabled);
    }

//    @Override
//    public String toString() {
//        return new StringBuilder("{")
//                .append("id=").append(id)
//                .append(", userName='").append(userName).append('\'')
//                .append(", password='").append(password).append('\'')
//                .append(", enabled=").append(enabled)
//                .append('}')
//                .toString();
//    }

}
