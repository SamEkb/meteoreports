package ru.skilanov.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Составной первичный ключ показаний метеостанции.
 */
@Embeddable
public class MeteoStationDataPk implements Serializable {
    /**
     * Идентификатор метеостанции с который были получены данные.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meteo_station_id")
    private MeteoStation meteoStationId;

    /**
     * Дата сбора метеоданных.
     */
    @Column(name = "read_timestamp")
    private Timestamp readTimestamp;

    /**
     * Температура.
     */
    @Column(name = "temperature")
    private double temperature;

    /**
     * Давление.
     */
    @Column(name = "pressure")
    private int pressure;

    /**
     * Направление ветра.
     */
    @Column(name = "wind_direction")
    private int windDirection;

    /**
     * Скорость ветра.
     */
    @Column(name = "wind_speed")
    private int windSpeed;

    public MeteoStationDataPk(MeteoStation meteoStationId, Timestamp readTimestamp, double temperature, int pressure,
                              int windDirection, int windSpeed) {
        this.meteoStationId = meteoStationId;
        this.readTimestamp = readTimestamp;
        this.temperature = temperature;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public MeteoStationDataPk() {
    }

    public MeteoStation getMeteoStationId() {
        return meteoStationId;
    }

    public void setMeteoStationId(MeteoStation meteoStationId) {
        this.meteoStationId = meteoStationId;
    }

    public Timestamp getReadTimestamp() {
        return readTimestamp;
    }

    public void setReadTimestamp(Timestamp readTimestamp) {
        this.readTimestamp = readTimestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeteoStationDataPk that = (MeteoStationDataPk) o;
        return Double.compare(that.temperature, temperature) == 0 &&
                pressure == that.pressure &&
                windDirection == that.windDirection &&
                windSpeed == that.windSpeed &&
                Objects.equals(meteoStationId, that.meteoStationId) &&
                Objects.equals(readTimestamp, that.readTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meteoStationId, readTimestamp, temperature, pressure, windDirection, windSpeed);
    }
}
