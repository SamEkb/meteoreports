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
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Константа 2.
     */
    private static final int TWO = 2;
    /**
     * Константа 1.
     */
    private static final int ZERO = 0;
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
     * Роль user.
     */
    private Role userRole;

    /**
     * роль admin.
     */
    private Role adminRole;

    /**
     * Метод проводит инициализацию перед каждым тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        roleDao = new RoleDaoImpl(factory);

        userRole = new Role(USER);
        adminRole = new Role(ADMIN);
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
        roleDao.insert(userRole);

        String result = roleDao.getAll().get(ZERO).getName();

        assertThat(result, is(USER));
    }

    /**
     * Тестирует получение списка всех ролей.
     */
    @Test
    public void whenGetAll_ThenReturnList() {
        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        List<Role> result = roleDao.getAll();

        List<Role> expected = new ArrayList<>();
        expected.add(userRole);
        expected.add(adminRole);

        assertThat(result.size(), is(TWO));
        assertThat(result.get(ONE).getName(), is(ADMIN));
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    /**
     * Тестирует удаление роли.
     */
    @Test
    public void whenDelete_ThenRoleDeleted() {
        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        roleDao.deleteRole(ONE);

        assertNull(roleDao.findById(ONE));
    }

    /**
     * Тестирует поиск роли по id.
     */
    @Test
    public void whenFindById_ThenReturnExpectedRole() {
        roleDao.insert(userRole);
        roleDao.insert(adminRole);

        Role resultRole = roleDao.findById(TWO);

        assertThat(adminRole, is(resultRole));
    }

    /**
     * Тестирует изменение роли.
     */
    @Test
    public void whenUpdateRole_ThenRoleIsUpdated() {
        roleDao.insert(userRole);

        Role modRole = roleDao.findById(ONE);
        modRole.setName(ADMIN);
        roleDao.update(modRole);

        String expectedRole = roleDao.getAll().get(ZERO).getName();

        assertThat(expectedRole, is(ADMIN));
    }
}
