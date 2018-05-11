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
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao метеостанции.
     */
    private MeteoStationDao meteoStationDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        meteoStationDao = new MeteoStationDaoImpl(factory);
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
        MeteoStation station = new MeteoStation(ARCTIC);

        meteoStationDao.insert(station);

        String expectedResultName = meteoStationDao.getAll().get(0).getName();

        assertThat(expectedResultName, is(ARCTIC));
    }

    /**
     * Тестирует получение списка всех метеостанций.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        MeteoStation station = new MeteoStation(ARCTIC);

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
        MeteoStation station = new MeteoStation(ARCTIC);

        meteoStationDao.insert(station);
        meteoStationDao.deleteStation(1);

        assertNull(meteoStationDao.findById(1));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightResult() {
        MeteoStation station = new MeteoStation(ARCTIC);

        meteoStationDao.insert(station);

        MeteoStation resultStation = meteoStationDao.findById(1);

        assertThat(resultStation, is(station));
    }

    /**
     * Тестирует изменение метеостанции.
     */
    @Test
    public void whenUpdate_ThenItUpdated() {
        MeteoStation station = new MeteoStation(ARCTIC);

        meteoStationDao.insert(station);
        MeteoStation modStation = meteoStationDao.findById(1);
        modStation.setName(EUROPE);
        meteoStationDao.update(modStation);

        String expectedStationName = meteoStationDao.getAll().get(0).getName();

        assertThat(expectedStationName, is(EUROPE));
    }
}
