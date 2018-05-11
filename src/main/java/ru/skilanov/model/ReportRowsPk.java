package ru.skilanov.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Составной первичный ключ строк отчета.
 */
@Embeddable
public class ReportRowsPk implements Serializable {
    /**
     * Идентификатор строки в отчете.
     */
    @Column(name = "row_id")
    private int row_id;

    /**
     * Идентификатор колонки к которой принадлежит строка.
     */
    @ManyToOne
    @JoinColumn(name = "column_id")
    private ReportColumns columnId;

    /**
     * Значение строки.
     */
    @Column(name = "row_value")
    private String value;

    public ReportRowsPk() {
    }

    public ReportRowsPk(int row_id, ReportColumns columnId, String value) {
        this.row_id = row_id;
        this.columnId = columnId;
        this.value = value;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

    public ReportColumns getColumnId() {
        return columnId;
    }

    public void setColumnId(ReportColumns columnId) {
        this.columnId = columnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportRowsPk that = (ReportRowsPk) o;
        return row_id == that.row_id &&
                Objects.equals(columnId, that.columnId) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(row_id, columnId, value);
    }
}
