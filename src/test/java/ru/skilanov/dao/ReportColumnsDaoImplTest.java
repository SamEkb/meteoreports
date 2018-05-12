package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует слой dao report columns.
 */
public class ReportColumnsDaoImplTest {
    /**
     * Константа колонки теспиратура.
     */
    private static final String TEMPERATURE = "Temperature";
    /**
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Константа колонки давление.
     */
    private static final String PRESSURE = "Pressure";
    /**
     * Константа 0.
     */
    private static final int ZERO = 0;
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Слой dao колонок.
     */
    private ReportColumnsDao reportColumnsDao;

    /**
     * Тестовые колонки.
     */
    private ReportColumns reportColumns;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        reportColumnsDao = new ReportColumnsDaoImpl(factory);
        MeteoStation station = new MeteoStation("Arctic");
        Role userRole = new Role("User");
        Users user = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), userRole);
        Reports reports = new Reports("header", "footer", station, user,
                new Timestamp(System.currentTimeMillis()));
        reportColumns = new ReportColumns("Temperature", reports, 1, "integer");
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
     * Тестирует добавление новой колонки.
     */
    @Test
    public void whenInsert_ThenItInserted() {
        reportColumnsDao.insert(reportColumns);

        String reportColumnName = reportColumnsDao.getAll().get(ZERO).getName();

        assertThat(reportColumnName, is(TEMPERATURE));
    }

    /**
     * Тестирует получение списка всех колонок.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        reportColumnsDao.insert(reportColumns);

        List<ReportColumns> result = reportColumnsDao.getAll();

        List<ReportColumns> reportColumnsList = new ArrayList<>();
        reportColumnsList.add(reportColumns);

        assertArrayEquals(result.toArray(), reportColumnsList.toArray());
    }

    /**
     * Тестирует удаление по id.
     */
    @Test
    public void whenDelete_ThenItDeleted() {
        reportColumnsDao.insert(reportColumns);
        reportColumnsDao.deleteColumn(ONE);

        assertNull(reportColumnsDao.findById(ONE));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightResult() {
        reportColumnsDao.insert(reportColumns);

        ReportColumns result = reportColumnsDao.findById(ONE);

        assertThat(result, is(reportColumns));
    }

    /**
     * Тестирует изменение колонки.
     */
    @Test
    public void whenUpdateColumn_ThenItUpdated() {
        reportColumnsDao.insert(reportColumns);

        ReportColumns modColumn = reportColumnsDao.findById(ONE);
        modColumn.setName(PRESSURE);
        reportColumnsDao.update(modColumn);

        String resultName = reportColumnsDao.getAll().get(ZERO).getName();

        assertThat(resultName, is(PRESSURE));
    }
}
