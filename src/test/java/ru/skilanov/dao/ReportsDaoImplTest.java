package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.MeteoStation;
import ru.skilanov.model.Reports;
import ru.skilanov.model.Role;
import ru.skilanov.model.Users;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует слой dao отчетов.
 */
public class ReportsDaoImplTest {
    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao отчетов.
     */
    private ReportsDao reportsDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        reportsDao = new ReportsDaoImpl(factory);
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
     * Тестирует добавление нового отчета.
     */
    @Test
    public void whenInsertNewReport_ThenItInserted() {
        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

        reportsDao.insert(report);

        String resultHeader = reportsDao.getAll().get(0).getHeader();

        assertThat(resultHeader, is("Header text"));
    }

    /**
     * Тестирует получение списка отчетов.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

        reportsDao.insert(report);

        List<Reports> result = reportsDao.getAll();

        List<Reports> expected = new ArrayList<>();
        expected.add(report);

        assertArrayEquals(result.toArray(), expected.toArray());
    }

    /**
     * Тестирует удаление отчета.
     */
    @Test
    public void whenDelete_ThenReportDeleted() {
        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

        reportsDao.insert(report);
        reportsDao.deleteReport(1);

        assertNull(reportsDao.findById(1));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightReport() {
        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

        reportsDao.insert(report);

        Reports resultReport = reportsDao.findById(1);

        assertThat(resultReport, is(report));
    }

    /**
     * Тестирует изменение отчета.
     */
    @Test
    public void whenUpdate_ThenItUpdated() {
        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

        reportsDao.insert(report);

        Reports modReport = reportsDao.findById(1);
        modReport.setHeader("Changed header text");
        reportsDao.update(modReport);

        String expectedHeader = reportsDao.getAll().get(0).getHeader();

        assertThat(expectedHeader, is("Changed header text"));
    }
}
