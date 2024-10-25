package com.example.ejbserveur.ejb.stateless;


import com.example.ejbserveur.JPAEnteties.CD;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CDService {

    @PersistenceContext
    private EntityManager em;

    public List<CD> getAvailableCDs() {
        return em.createQuery("SELECT c FROM CD c WHERE c.isBorrowed = FALSE", CD.class).getResultList();
    }

    public void borrowCD(Long userId, Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && !cd.isBorrowed()) {
            cd.setBorrowed(true);
            em.merge(cd);
        }
    }

    public void returnCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isBorrowed()) {
            cd.setBorrowed(false);
            em.merge(cd);
        }
    }
}

