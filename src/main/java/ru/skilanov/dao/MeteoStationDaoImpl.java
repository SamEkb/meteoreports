package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.MeteoStation;

import java.util.List;

/**
 * Реализация интерфейса MeteoStationDao.
 */
public class MeteoStationDaoImpl implements MeteoStationDao {
    /**
     * Фабрика соединений hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     *
     * @param factory SessionFactory
     */
    public MeteoStationDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод возвращает список метеостанций.
     *
     * @return List
     */
    @Override
    public List<MeteoStation> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<MeteoStation> meteoStations = session.createQuery("from MeteoStation ").list();

            session.getTransaction().commit();

            return meteoStations;
        }
    }

    /**
     * Метод добавляет новую метеостанцию.
     *
     * @param station MeteoStation
     */
    @Override
    public void insert(MeteoStation station) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(station);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаляет метеостанцию.
     *
     * @param id int
     */
    @Override
    public void deleteStation(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            MeteoStation station = session.get(MeteoStation.class, id);
            session.delete(station);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет метеостанцию.
     *
     * @param station MeteoStation
     */
    @Override
    public void update(MeteoStation station) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(station);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска метеостанции по id.
     *
     * @param id int
     * @return MeteoStation
     */
    @Override
    public MeteoStation findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            MeteoStation station = session.get(MeteoStation.class, id);

            return station;
        }
    }
}
