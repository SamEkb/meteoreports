package ru.skilanov.model;

import org.hibernate.annotations.Table;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * Строки отчета.
 */
@Entity
@Table(appliesTo = "report_rows")
public class ReportRows {

    /**
     * Идентификатор строк отчета.
     */
    @EmbeddedId
    private ReportRowsPk id;

    public ReportRows() {
    }

    public ReportRows(ReportRowsPk id) {
        this.id = id;
    }

    public ReportRowsPk getId() {
        return id;
    }

    public void setId(ReportRowsPk id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReportRows{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportRows that = (ReportRows) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
