package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.ReportColumns;

import java.util.List;

/**
 * Реализация интерфейса ReportColumnsDao.
 */
public class ReportColumnsDaoImpl implements ReportColumnsDao {
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     *
     * @param factory SessionFactory
     */
    public ReportColumnsDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод добавляет новую колонку.
     *
     * @param columns ReportColumns
     */
    @Override
    public void insert(ReportColumns columns) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(columns);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод возвращает список всех колонок.
     *
     * @return List
     */
    @Override
    public List<ReportColumns> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<ReportColumns> columns = session.createQuery("from ReportColumns ").list();

            session.getTransaction().commit();

            return columns;
        }
    }

    /**
     * Метод удаляет колонку.
     *
     * @param id int
     */
    @Override
    public void deleteColumn(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            ReportColumns column = session.get(ReportColumns.class, id);
            session.delete(column);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет колонку.
     *
     * @param column ReportColumns
     */
    @Override
    public void update(ReportColumns column) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(column);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска колонки по id.
     *
     * @param id int
     * @return ReportColumns
     */
    @Override
    public ReportColumns findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            ReportColumns column = session.get(ReportColumns.class, id);

            session.getTransaction().commit();

            return column;
        }
    }
}
