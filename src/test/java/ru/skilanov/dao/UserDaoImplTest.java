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
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Константа 0.
     */
    private static final int ZERO = 0;
    /**
     * Константа логин.
     */
    private static final String LOGIN = "User";
    /**
     * Константа имя пользователя.
     */
    private static final String NAME = "Sam Kilanoff";
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao пользователя.
     */
    private UsersDao usersDao;

    /**
     * Пользователь sam.
     */
    private Users userSam;

    /**
     * Пользователь mike.
     */
    private Users userMike;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        usersDao = new UsersDaoImpl(factory);
        userSam = new Users("Sam", "Pass", "Sam Kilanoff",
                new Timestamp(System.currentTimeMillis()), new Role("User"));
        userMike = new Users("Mike", "Pass", "Mike Johns",
                new Timestamp(System.currentTimeMillis()), new Role("User"));
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
        usersDao.insert(userSam);

        String expectedName = usersDao.getAll().get(ZERO).getName();

        assertThat(expectedName, is(NAME));
    }

    /**
     * Тестирует получение списка всех пользователей.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
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
        usersDao.insert(userSam);
        usersDao.deleteUser(ONE);
        assertNull(usersDao.findById(ONE));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenReturnExpectedUser() {
        usersDao.insert(userSam);

        Users expectedUser = usersDao.findById(ONE);

        assertThat(expectedUser, is(userSam));
    }

    /**
     * Тестирует изменение польщователя.
     */
    @Test
    public void whenUpdateUser_ThenItUpdated() {
        usersDao.insert(userSam);

        Users expectedUser = usersDao.findById(ONE);
        expectedUser.setLogin(LOGIN);
        usersDao.update(expectedUser);

        String resultLogin = usersDao.getAll().get(ZERO).getLogin();

        assertThat(resultLogin, is(LOGIN));
    }
}
