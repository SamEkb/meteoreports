package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.skilanov.model.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private SessionFactory factory;

    public RoleDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Role> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Role> roles = session.createQuery("from Role ").list();

            session.getTransaction().commit();

            return roles;
        }
    }

    @Override
    public void insert(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(role);

            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteRole(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Role role = session.get(Role.class, id);
            session.delete(role);

            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Role role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(role);

            session.getTransaction().commit();
        }
    }

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
