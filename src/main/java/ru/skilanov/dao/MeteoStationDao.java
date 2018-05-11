package ru.skilanov.dao;

import ru.skilanov.model.MeteoStation;

import java.util.List;

/**
 * Слой dao для meteostation.
 */
public interface MeteoStationDao {
    /**
     * Метод возвращает список метеостанций.
     *
     * @return List
     */
    List<MeteoStation> getAll();

    /**
     * Метод добавляет новую метеостанцию.
     *
     * @param station MeteoStation
     */
    void insert(MeteoStation station);

    /**
     * Метод удаляет метеостанцию.
     *
     * @param id int
     */
    void deleteStation(int id);

    /**
     * Метод обновляет метеостанцию.
     *
     * @param station MeteoStation
     */
    void update(MeteoStation station);

    /**
     * Метод поиска метеостанции по id.
     *
     * @param id int
     * @return MeteoStation
     */
    MeteoStation findById(int id);
}
