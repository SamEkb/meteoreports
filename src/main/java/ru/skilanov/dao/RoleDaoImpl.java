package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.Role;

import java.util.List;

/**
 * Реализация интерфейса RoleDao.
 */
public class RoleDaoImpl implements RoleDao {
    /**
     * Фабрика соединений hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     * @param factory SessionFactory
     */
    public RoleDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод возвращает список всех ролей.
     *
     * @return List
     */
    @Override
    public List<Role> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Role> roles = session.createQuery("from Role ").list();

            session.getTransaction().commit();

            return roles;
        }
    }

    /**
     * Метод добавляет новую роль.
     *
     * @param role Role
     */
    @Override
    public void insert(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(role);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаляет роль.
     *
     * @param id int
     */
    @Override
    public void deleteRole(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Role role = session.get(Role.class, id);
            session.delete(role);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет роль.
     *
     * @param role Role
     */
    @Override
    public void update(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(role);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска роли по id.
     *
     * @param id int
     * @return Role
     */
    @Override
    public Role findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Role role = session.get(Role.class, id);

            session.getTransaction().commit();

            return role;
        }
    }
}
