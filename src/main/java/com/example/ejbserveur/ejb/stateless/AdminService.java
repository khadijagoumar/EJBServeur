package com.example.ejbserveur.ejb.stateless;


import com.example.ejbserveur.JPAEnteties.Book;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
public class AdminService {

    @PersistenceContext
    private EntityManager em;

    public void addBook(Book book) {
        em.persist(book);
    }

    public void updateBook(Book book) {
        em.merge(book);
    }

    public void deleteBook(Long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            em.remove(book);
        }
    }
}
