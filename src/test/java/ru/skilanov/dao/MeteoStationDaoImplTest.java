package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.MeteoStation;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует dao meteostation.
 */
public class MeteoStationDaoImplTest {
    /**
     * Константа арктической метеостанции.
     */
    private static final String ARCTIC = "Arctic";
    /**
     * Константа европейской метеостанции.
     */
    private static final String EUROPE = "Europe";
    /**
     * Константа 0.
     */
    private static final int ZERO = 0;
    /**
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao метеостанции.
     */
    private MeteoStationDao meteoStationDao;

    /**
     * Константа метеостанции.
     */
    private MeteoStation station;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        meteoStationDao = new MeteoStationDaoImpl(factory);

        station = new MeteoStation(ARCTIC);
    }

    /**
     * После теста.
     *
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        factory.close();
    }

    /**
     * Тестирует добавление новой метеостанции.
     */
    @Test
    public void whenInsertMeteoStation_ThenItInserted() {
        meteoStationDao.insert(station);

        String expectedResultName = meteoStationDao.getAll().get(ZERO).getName();

        assertThat(expectedResultName, is(ARCTIC));
    }

    /**
     * Тестирует получение списка всех метеостанций.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        meteoStationDao.insert(station);

        List<MeteoStation> result = meteoStationDao.getAll();

        List<MeteoStation> expected = new ArrayList<>();
        expected.add(station);

        assertThat(result.size(), is(expected.size()));
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    /**
     * Тестирует удаление по id.
     */
    @Test
    public void whenDelete_ThenMeteoStationDeleted() {
        meteoStationDao.insert(station);
        meteoStationDao.deleteStation(ONE);

        assertNull(meteoStationDao.findById(ONE));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightResult() {
        meteoStationDao.insert(station);

        MeteoStation resultStation = meteoStationDao.findById(ONE);

        assertThat(resultStation, is(station));
    }

    /**
     * Тестирует изменение метеостанции.
     */
    @Test
    public void whenUpdate_ThenItUpdated() {
        meteoStationDao.insert(station);
        MeteoStation modStation = meteoStationDao.findById(ONE);
        modStation.setName(EUROPE);
        meteoStationDao.update(modStation);

        String expectedStationName = meteoStationDao.getAll().get(ZERO).getName();

        assertThat(expectedStationName, is(EUROPE));
    }
}
