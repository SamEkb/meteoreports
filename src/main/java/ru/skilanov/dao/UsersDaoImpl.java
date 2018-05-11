package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.Users;

import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private SessionFactory factory;

    public UsersDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Users> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Users> users = session.createQuery("FROM Users ").list();

            session.getTransaction().commit();

            return users;
        }
    }

    @Override
    public void insert(Users user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Users user = session.get(Users.class, id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Users user) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(user);

        session.getTransaction().commit();
    }

    @Override
    public Users findById(int id) {
        Session session = factory.openSession();
        session.beginTransaction();

        Users user = session.get(Users.class, id);

        session.getTransaction().commit();
        return user;
    }
}
