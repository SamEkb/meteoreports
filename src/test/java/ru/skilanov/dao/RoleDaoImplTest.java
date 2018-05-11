package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.Role;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует слой dao роли.
 */
public class RoleDaoImplTest {

    /**
     * Константа роли пользователь.
     */
    private static final String USER = "User";
    /**
     * Константа роли администратор.
     */
    private static final String ADMIN = "Admin";
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;
    /**
     * Dao роли.
     */
    private RoleDao roleDao;

    /**
     * Метод проводит инициализацию перед каждым тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        roleDao = new RoleDaoImpl(factory);
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
     * Тестирует метод insert.
     */
    @Test
    public void whenInsertRole_ThenItInserted() {
        Role userRole = new Role(USER);

        roleDao.insert(userRole);

        String result = roleDao.getAll().get(0).getName();

        assertThat(result, is(USER));
    }

    /**
     * Тестирует получение списка всех ролей.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        Role userRole = new Role(USER);
        Role adminRole = new Role(ADMIN);

        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        List<Role> result = roleDao.getAll();

        List<Role> expected = new ArrayList<>();
        expected.add(userRole);
        expected.add(adminRole);

        assertThat(result.size(), is(2));
        assertThat(result.get(1).getName(), is(ADMIN));
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    /**
     * Тестирует удаление роли.
     */
    @Test
    public void whenDelete_ThenRoleDeleted() {
        Role userRole = new Role(USER);
        Role adminRole = new Role(ADMIN);

        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        roleDao.deleteRole(1);

        assertNull(roleDao.findById(1));
    }

    /**
     * Тестирует поиск роли по id.
     */
    @Test
    public void whenFindById_ThenReturnExpectedRole() {
        Role userRole = new Role(USER);
        Role adminRole = new Role(ADMIN);

        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        Role resultRole = roleDao.findById(2);

        assertThat(adminRole, is(resultRole));
    }

    /**
     * Тестирует изменение роли.
     */
    @Test
    public void whenUpdateRole_ThenRoleIsUpdated() {
        Role userRole = new Role(USER);

        roleDao.insert(userRole);

        Role modRole = roleDao.findById(1);
        modRole.setName(ADMIN);
        roleDao.update(modRole);

        String expectedRole = roleDao.getAll().get(0).getName();

        assertThat(expectedRole, is(ADMIN));
    }
}
