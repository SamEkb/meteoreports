package ru.skilanov.dao;

import ru.skilanov.model.ReportColumns;

import java.util.List;

/**
 * Слой dao колонок отчета.
 */
public interface ReportColumnsDao {
    /**
     * Метод добавляет новую колонку.
     *
     * @param columns ReportColumns
     */
    void insert(ReportColumns columns);

    /**
     * Метод возвращает список всех колонок.
     *
     * @return List
     */
    List<ReportColumns> getAll();


    /**
     * Метод удаляет колонку.
     *
     * @param id int
     */
    void deleteColumn(int id);

    /**
     * Метод обновляет колонку.
     *
     * @param column ReportColumns
     */
    void update(ReportColumns column);

    /**
     * Метод поиска колонки по id.
     *
     * @param id int
     * @return ReportColumns
     */
    ReportColumns findById(int id);
}
