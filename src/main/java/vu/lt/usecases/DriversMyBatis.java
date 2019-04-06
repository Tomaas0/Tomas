package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.DriversMapper;
import vu.lt.mybatis.model.Drivers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DriversMyBatis {
    @Inject
    private DriversMapper driversMapper;

    @Getter
    private List<Drivers> allDrivers;

    @Getter @Setter
    private Drivers driverToCreate = new Drivers();

    @PostConstruct
    public void init() {
        this.loadAllDrivers();
    }

    private void loadAllDrivers() {
        this.allDrivers = driversMapper.selectAll();
    }

    @Transactional
    public String createDriver() {
        driversMapper.insert(driverToCreate);
        return "/myBatis/drivers?faces-redirect=true";
    }
}
