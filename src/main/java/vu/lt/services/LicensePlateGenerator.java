package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped
public class LicensePlateGenerator implements Serializable {

    @Futureable
    public Future<String> generateLicensePlate() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }
        final String generatedLicensePlateNumber = Integer.toString(new Random().nextInt(899) + 100);
        return new AsyncResult<>("LFO" + generatedLicensePlateNumber);
    }
}