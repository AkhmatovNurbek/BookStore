package uz.java_ee.dao;

import org.hibernate.Session;
import uz.java_ee.configs.HibernateConfigurer;
import uz.java_ee.domains.Book;

import java.util.List;
import java.util.Objects;

public class BookDao implements Dao<Book> {

    @Override
    public Book create(Book entity) {
        Session session = HibernateConfigurer.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateConfigurer.getSession();
        session.getTransaction().begin();
        Book book = session.find(Book.class, id);
        if (Objects.nonNull(book))
            session.remove(book);
        session.getTransaction().commit();
    }

    @Override
    public List<Book> findAll() {
        Session session = HibernateConfigurer.getSession();
        return session.createQuery("select T from Book T order by T.id desc ", Book.class).getResultList();
    }

    @Override
    public Book findOne(Long id) {
        return null;
    }

    public void updateCoverId(long bookId, Long coverId) {
        Session session = HibernateConfigurer.getSession();
        session.getTransaction().begin();
        session.createNativeQuery("update book set cover_id = :cover_id where id = :id returning id", Long.class)
                .setParameter("id", bookId)
                .setParameter("cover_id", coverId)
                .getSingleResultOrNull();
        session.getTransaction().commit();
    }
}
