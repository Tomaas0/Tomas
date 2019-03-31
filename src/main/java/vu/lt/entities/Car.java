package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select c from Car as c")
})
@Table(name = "CARS")
@Getter @Setter
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="DRIVER_ID")
    private Driver driver;

    public Car() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
