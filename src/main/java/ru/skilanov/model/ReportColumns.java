package ru.skilanov.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * Модель данных колонок включенных в отчет.
 */
@Entity
@Table(name = "report_columns")
public class ReportColumns {
    /**
     * Идентификатор колонки отчета.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя.
     */
    @Column(name = "column_name")
    private String name;

    /**
     * Идентификатор отчета.
     */
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Reports reportId;

    /**
     * Номер в отчете.
     */
    @Column(name = "order_number")
    private int orderNumber;

    /**
     * Тип данных хранящихся в колонке.
     */
    @Column(name = "type")
    private String type;

    public ReportColumns() {
    }

    public ReportColumns(int id, String name, Reports reportId, int orderNumber, String type) {
        this.id = id;
        this.name = name;
        this.reportId = reportId;
        this.orderNumber = orderNumber;
        this.type = type;
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

    public Reports getReportId() {
        return reportId;
    }

    public void setReportId(Reports reportId) {
        this.reportId = reportId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReportColumns{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reportId=" + reportId +
                ", orderNumber=" + orderNumber +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportColumns that = (ReportColumns) o;
        return id == that.id &&
                orderNumber == that.orderNumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(reportId, that.reportId) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, reportId, orderNumber, type);
    }
}
