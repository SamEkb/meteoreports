package ru.skilanov.dao;

import ru.skilanov.model.Role;

import java.util.List;

/**
 * Слой dao для role.
 */
public interface RoleDao {
    /**
     * Метод возвращает список всех ролей.
     *
     * @return List
     */
    List<Role> getAll();

    /**
     * Метод добавляет новую роль.
     *
     * @param role Role
     */
    void insert(Role role);

    /**
     * Метод удаляет роль.
     *
     * @param id int
     */
    void deleteRole(int id);

    /**
     * Метод обновляет роль.
     *
     * @param role Role
     */
    void update(Role role);

    /**
     * Метод поиска роли по id.
     *
     * @param id int
     * @return Role
     */
    Role findById(int id);
}
