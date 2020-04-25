package model;

import checkers.units.quals.C;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Car {
    private int id;
    private String model;
    private String brand;
    private Date dateProduction;
    private String idNumber;
    private Date nextCheckupDate;
    private Client client;

    public static class Builder {
        private final String model;
        private final String brand;
        private final Date dateProduction;
        private final String idNumber;
        private final Client client;


        private Date nextCheckupDate;


        public Builder(String model, String brand, Date dateProduction, String idNumber, Client client) {
            this.model = model;
            this.brand = brand;
            this.dateProduction = dateProduction;
            this.idNumber = idNumber;
            this.client = client;
        }


        public Builder nextCheckupDate(Date val) {
            nextCheckupDate = val;
            return this;

        }

        public Car build() {
            return new Car(this);

        }
    }

    private Car(Builder builder) {
        model = builder.model;
        brand = builder.brand;
        dateProduction = builder.dateProduction;
        idNumber = builder.idNumber;
        nextCheckupDate = builder.nextCheckupDate;
        client = builder.client;
    }
}


