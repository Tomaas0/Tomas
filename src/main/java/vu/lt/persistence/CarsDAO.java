package vu.lt.persistence;

import vu.lt.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CarsDAO {

    @Inject
    private EntityManager em;

    public void persist(Car car){
        this.em.persist(car);
    }

    public Car findOne(Integer id) {
        return em.find(Car.class, id);
    }
}
