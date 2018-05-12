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
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Слой dao колонок.
     */
    private ReportColumnsDao reportColumnsDao;

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

        String reportColumnName = reportColumnsDao.getAll().get(0).getName();

        assertThat(reportColumnName, is("Temperature"));
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
        reportColumnsDao.deleteColumn(1);

        assertNull(reportColumnsDao.findById(1));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnRightResult() {
        reportColumnsDao.insert(reportColumns);

        ReportColumns result = reportColumnsDao.findById(1);

        assertThat(result, is(reportColumns));
    }

    /**
     * Тестирует изменение колонки.
     */
    @Test
    public void whenUpdateColumn_ThenItUpdated(){
        reportColumnsDao.insert(reportColumns);

        ReportColumns modColumn = reportColumnsDao.findById(1);
        modColumn.setName("Pressure");
        reportColumnsDao.update(modColumn);

        String resultName = reportColumnsDao.getAll().get(0).getName();

        assertThat(resultName, is("Pressure"));
    }
}
