package ru.skilanov.model;



import javax.persistence.*;
import java.util.Objects;

/**
 * Модель нанных метеостанции.
 */
@Entity
@Table(name = "meteo_station")
public class MeteoStation {
    /**
     * Идентификатор метеостанции.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Наименование метеостанции.
     */
    @Column(name = "name")
    private String name;

    public MeteoStation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MeteoStation() {
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
        return "MeteoStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeteoStation that = (MeteoStation) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
