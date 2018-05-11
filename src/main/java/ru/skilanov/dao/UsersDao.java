package ru.skilanov.dao;

import ru.skilanov.model.Users;

import java.util.List;

/**
 * Слой dao для user.
 */
public interface UsersDao {
    /**
     * Метод возвращает список всех пользователей.
     *
     * @return List
     */
    List<Users> getAll();

    /**
     * Метод добавляет нового пользователя.
     *
     * @param user User
     */
    void insert(Users user);

    /**
     * Метод удаляет пользователя.
     *
     * @param id int
     */
    void deleteUser(int id);

    /**
     * Метод обновляет пользователя.
     *
     * @param user User
     */
    void update(Users user);

    /**
     * Метод поиска пользователя по id.
     *
     * @param id int
     * @return User
     */
    Users findById(int id);
}
