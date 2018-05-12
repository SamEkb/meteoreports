package ru.skilanov.dao;

import ru.skilanov.model.Reports;

import java.util.List;

/**
 * Слой dao для reports.
 */
public interface ReportsDao {
    /**
     * Метод возвращает список всех отчетов.
     *
     * @return List
     */
    List<Reports> getAll();

    /**
     * Метод добавляет новый отчет.
     *
     * @param report Reports
     */
    void insert(Reports report);

    /**
     * Метод удаляет отчет.
     *
     * @param id int
     */
    void deleteReport(int id);

    /**
     * Метод обновляет отчет.
     *
     * @param report Reports
     */
    void update(Reports report);

    /**
     * Метод поиска отчета по id.
     *
     * @param id int
     * @return Report
     */
    Reports findById(int id);
}
