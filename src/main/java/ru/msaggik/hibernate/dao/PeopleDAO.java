package ru.msaggik.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.msaggik.hibernate.models.Person;


import java.util.List;

@Component
public class PeopleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PeopleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // возврат всех пользователей из БД
    @Transactional(readOnly = true) // данная аннотация открывает транзакцию внутри помеченного метода,
    // (readOnly = true) - означает, что разрешенно только чтение данных
    public List<Person> index() {
        // открытие сессии
        Session session = sessionFactory.getCurrentSession();
        // получение из БД информации всех пользователей (вручную)
        List<Person> people = session.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
        return people;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {

    }
    // обновление данных пользователя
    public void update(int id, Person updatedPerson) {

    }
    // удаление данных пользователя
    public void delete(int id) {

    }

}
