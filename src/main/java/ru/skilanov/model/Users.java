package ru.skilanov.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Модель данных пользователей системы.
 */
@Entity
@Table(name = "users")
public class Users {
    /**
     * Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Логин пользователя.
     */
    @Column(name = "login")
    private String login;

    /**
     * Пароль пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя.
     */
    @Column(name = "user_name")
    private String name;

    /**
     * Дата регистрации.
     */
    @Column(name = "registration_date")
    private Timestamp registrationDate;

    /**
     * Идентификатор роли.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Role roleId;

    public Users(String login, String password, String name, Timestamp registrationDate, Role roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.registrationDate = registrationDate;
        this.roleId = roleId;
    }

    public Users(int id, String login, String password, String name, Timestamp registrationDate, Role roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.registrationDate = registrationDate;
        this.roleId = roleId;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(login, users.login) &&
                Objects.equals(password, users.password) &&
                Objects.equals(name, users.name) &&
                Objects.equals(registrationDate, users.registrationDate) &&
                Objects.equals(roleId, users.roleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, name, registrationDate, roleId);
    }
}
