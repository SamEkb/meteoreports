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
     * Константа шапки.
     */
    private static final String HEADER = "Header text";
    /**
     * Константа 0.
     */
    private static final int ZERO = 0;
    /**
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Константа измененной шапки.
     */
    private static final String CHANGED_HEADER_TEXT = "Changed header text";
    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao отчетов.
     */
    private ReportsDao reportsDao;

    /**
     * Тестовый отчет.
     */
    private Reports report;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        reportsDao = new ReportsDaoImpl(factory);

        MeteoStation arcticStation = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users userSam = new Users("Sam", "pass", "User1990",
                new Timestamp(System.currentTimeMillis()), userRole);
        report = new Reports("Header text", "Footer text", arcticStation, userSam,
                new Timestamp(System.currentTimeMillis()));

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
        reportsDao.insert(report);

        String resultHeader = reportsDao.getAll().get(ZERO).getHeader();

        assertThat(resultHeader, is(HEADER));
    }

    /**
     * Тестирует получение списка отчетов.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
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
        reportsDao.insert(report);
        reportsDao.deleteReport(ONE);

        assertNull(reportsDao.findById(ONE));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightReport() {
        reportsDao.insert(report);

        Reports resultReport = reportsDao.findById(ONE);

        assertThat(resultReport, is(report));
    }

    /**
     * Тестирует изменение отчета.
     */
    @Test
    public void whenUpdate_ThenItUpdated() {
        reportsDao.insert(report);

        Reports modReport = reportsDao.findById(ONE);
        modReport.setHeader(CHANGED_HEADER_TEXT);
        reportsDao.update(modReport);

        String expectedHeader = reportsDao.getAll().get(ZERO).getHeader();

        assertThat(expectedHeader, is(CHANGED_HEADER_TEXT));
    }
}
