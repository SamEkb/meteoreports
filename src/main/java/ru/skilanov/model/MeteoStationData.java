package ru.skilanov.model;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Модель данных показаний с метеостанции.
 */
@Entity
@Table(name = "meteo_station_data")
public class MeteoStationData {

    /**
     * Составной первичный ключ данных метеостанции.
     */
    @EmbeddedId
    private MeteoStationDataPk id;

    public MeteoStationData() {
    }

    public MeteoStationData(MeteoStationDataPk id) {
        this.id = id;
    }

    public MeteoStationDataPk getId() {
        return id;
    }

    public void setId(MeteoStationDataPk id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeteoStationData that = (MeteoStationData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
