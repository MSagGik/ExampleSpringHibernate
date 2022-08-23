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

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    // обновление данных пользователя
    @Transactional
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);

    }
    // удаление данных пользователя
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }
}
