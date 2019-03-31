package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Driver.findAll", query = "select d from Driver as d")
})
@Table(name = "DRIVERS")
@Getter @Setter
public class Driver {

    public Driver(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
