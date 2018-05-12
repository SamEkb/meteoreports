package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.Reports;

import java.util.List;

/**
 * Реализация интерфейса ReportsDao.
 */
public class ReportsDaoImpl implements ReportsDao {
    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     *
     * @param factory SessionFactory
     */
    public ReportsDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод возвращает список всех отчетов.
     *
     * @return List
     */
    @Override
    public List<Reports> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Reports> reports = session.createQuery("from Reports ").list();

            session.getTransaction().commit();

            return reports;
        }
    }

    /**
     * Метод добавляет новый отчет.
     *
     * @param report Reports
     */
    @Override
    public void insert(Reports report) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(report);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаляет отчет.
     *
     * @param id int
     */
    @Override
    public void deleteReport(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Reports report = session.get(Reports.class, id);
            session.delete(report);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет отчет.
     *
     * @param report Reports
     */
    @Override
    public void update(Reports report) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(report);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска отчета по id.
     *
     * @param id int
     * @return Report
     */
    @Override
    public Reports findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Reports report = session.get(Reports.class, id);

            session.getTransaction().commit();

            return report;
        }
    }
}
