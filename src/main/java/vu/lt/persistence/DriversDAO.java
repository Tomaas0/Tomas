package vu.lt.persistence;

import vu.lt.entities.Driver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class DriversDAO {

    @Inject
    private EntityManager em;

    public List<Driver> loadAll() {
        return em.createNamedQuery("Driver.findAll", Driver.class).getResultList();
    }

    public void persist(Driver driver){
        this.em.persist(driver);
    }

    public Driver findOne(Integer id) {
        return em.find(Driver.class, id);
    }
}
