package ru.skilanov.model;

import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Модель данных роли пользователя системы.
 */
@Entity
@Table(appliesTo = "role")
public class Role {
    /**
     * Идентификатор роли.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Наименование роли.
     */
    @Column(name = "name")
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
