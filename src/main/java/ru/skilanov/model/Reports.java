package ru.skilanov.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Меодель данных отчета.
 */
@Entity
@Table(name = "reports")
public class Reports {
    /**
     * Идентификатор отчета.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Информация для "шапки" отчета.
     */
    @Column(name = "header")
    private String header;

    /**
     * Информация для "подвала" отчета.
     */
    @Column(name = "footer")
    private String footer;

    /**
     * Идентификатор мтеостанции по которой будет формироваться отчет.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meteo_station_id")
    private MeteoStation meteoStationId;

    /**
     * Идентификатор пользователя, который формирует отчет.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users userId;

    /**
     * Дата создания отчета.
     */
    @Column(name = "creation_date")
    private Timestamp creationdate;

    public Reports() {
    }

    public Reports(int id, String header, String footer, MeteoStation meteoStationId, Users userId, Timestamp creationdate) {
        this.id = id;
        this.header = header;
        this.footer = footer;
        this.meteoStationId = meteoStationId;
        this.userId = userId;
        this.creationdate = creationdate;
    }

    public Reports(String header, String footer, MeteoStation meteoStationId, Users userId, Timestamp creationdate) {
        this.header = header;
        this.footer = footer;
        this.meteoStationId = meteoStationId;
        this.userId = userId;
        this.creationdate = creationdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public MeteoStation getMeteoStationId() {
        return meteoStationId;
    }

    public void setMeteoStationId(MeteoStation meteoStationId) {
        this.meteoStationId = meteoStationId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                ", meteoStationId=" + meteoStationId +
                ", userId=" + userId +
                ", creationdate=" + creationdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reports reports = (Reports) o;
        return id == reports.id &&
                Objects.equals(header, reports.header) &&
                Objects.equals(footer, reports.footer) &&
                Objects.equals(meteoStationId, reports.meteoStationId) &&
                Objects.equals(userId, reports.userId) &&
                Objects.equals(creationdate, reports.creationdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, footer, meteoStationId, userId, creationdate);
    }
}
