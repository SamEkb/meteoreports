package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.Users;

import java.util.List;

/**
 * Реализация интерфейса UsersDao.
 */
public class UsersDaoImpl implements UsersDao {
    /**
     * Фабрика подключений hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     *
     * @param factory SessionFactory
     */
    public UsersDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод возвращает список всех пользователей.
     *
     * @return List
     */
    @Override
    public List<Users> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Users> users = session.createQuery("FROM Users ").list();

            session.getTransaction().commit();

            return users;
        }
    }

    /**
     * Метод добавляет нового пользователя.
     *
     * @param user User
     */
    @Override
    public void insert(Users user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаляет пользователя.
     *
     * @param id int
     */
    @Override
    public void deleteUser(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Users user = session.get(Users.class, id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет пользователя.
     *
     * @param user User
     */
    @Override
    public void update(Users user) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(user);

        session.getTransaction().commit();
    }

    /**
     * Метод поиска пользователя по id.
     *
     * @param id int
     * @return User
     */
    @Override
    public Users findById(int id) {
        Session session = factory.openSession();
        session.beginTransaction();

        Users user = session.get(Users.class, id);

        session.getTransaction().commit();
        return user;
    }
}
