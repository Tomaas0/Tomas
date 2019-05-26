package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Car;
import vu.lt.persistence.CarsDAO;
import vu.lt.rest.contracts.CarDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/cars")
public class CarController {

    @Inject
    @Setter @Getter
    private CarsDAO carsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Car car = carsDAO.findOne(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        System.out.println(car);

        CarDto carDto = new CarDto();
        carDto.setName(car.getName());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setDriver(car.getDriver().getName());

        System.out.println(carDto.getName());

        return Response.ok(carDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer carId,
            CarDto carData) {
        try {
            Car existingCar = carsDAO.findOne(carId);
            if (existingCar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCar.setName(carData.getName());
            existingCar.setLicensePlate(carData.getLicensePlate());
            carsDAO.merge(existingCar);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}