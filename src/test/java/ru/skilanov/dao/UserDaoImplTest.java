package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.Role;
import ru.skilanov.model.Users;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует dao пользователя.
 */
public class UserDaoImplTest {
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao пользователя.
     */
    private UsersDao usersDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        usersDao = new UsersDaoImpl(factory);
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
     * Тестирует добавление нового пользователя.
     */
    @Test
    public void whenInsertNewUser_ThenItInserted() {
        Users userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));

        usersDao.insert(userSam);

        String expectedName = usersDao.getAll().get(0).getName();

        assertThat(expectedName, is("Sam Kilanoff"));
    }

    /**
     * Тестирует получение списка всех пользователей.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        Users userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));
        Users userMike = new Users("Mike", "Pass", "Mike Johns",
                new Timestamp(System.currentTimeMillis()), new Role("User"));

        usersDao.insert(userSam);
        usersDao.insert(userMike);

        List<Users> result = usersDao.getAll();

        List<Users> expected = new ArrayList<>();
        expected.add(userSam);
        expected.add(userMike);

        assertThat(result.size(), is(expected.size()));
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    /**
     * Тестирует удаление пользователя.
     */
    @Test
    public void whenDelete_ThenUserDeleted() {
        Users userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));

        usersDao.insert(userSam);
        usersDao.deleteUser(1);
        assertNull(usersDao.findById(1));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnExpectedUser() {
        Users userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));

        usersDao.insert(userSam);

        Users expectedUser = usersDao.findById(1);

        assertThat(expectedUser, is(userSam));
    }

    /**
     * Тестирует изменение польщователя.
     */
    @Test
    public void whenUpdateUser_ThenItUpdated() {
        Users userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));

        usersDao.insert(userSam);

        Users expectedUser = usersDao.findById(1);
        expectedUser.setLogin("User");
        usersDao.update(expectedUser);

        String resultLogin = usersDao.getAll().get(0).getLogin();

        assertThat(resultLogin, is("User"));
    }
}
