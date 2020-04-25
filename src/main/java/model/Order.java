package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Order {
    private long id;
    private LocalDate dateReceived;
    private LocalDate plannedDateStartRepair;
    private LocalDate dateStartRepair;
    private Employee employeeAssigned;
    private String problemDes;
    private String resolutionDes;
    private Status status;
    private Car car;
    private double costRepair;
    private double costOfItems;
    private double costOfHour;
    private double workHours;

    public static class Builder {
        private final LocalDate dateReceived;
        private final LocalDate plannedDateStartRepair;
        private final String problemDes;
        private final Status status;
        private final Car car;
        private final Employee employeeAssigned;


        private LocalDate dateStartRepair = LocalDate.of(0000, 01, 01);
        private String resolutionDes = null;
        private double costRepair = 0;
        private double costOfItems = 0;
        private double costOfHour = 0;
        private double workHours = 0;

        public Builder(LocalDate dateReceived, LocalDate plannedDateStartRepair, String problemDes, Status status, Car car, Employee employeeAssigned) {
            this.dateReceived = dateReceived;
            this.plannedDateStartRepair = plannedDateStartRepair;
            this.problemDes = problemDes;
            this.status = status;
            this.car = car;
            this.employeeAssigned = employeeAssigned;
        }


        public Builder dateStartRepair(LocalDate val) {
            dateStartRepair = val;
            return this;
        }

        public Builder resolutionDes(String val) {
            resolutionDes = val;
            return this;
        }

        public Builder costRepair(double val) {
            costRepair = val;
            return this;
        }

        public Builder costOfItems(double val) {
            costOfItems = val;
            return this;
        }

        public Builder costOfHour(double val) {
            costOfHour = val;
            return this;
        }

        public Builder workHours(double val) {
            workHours = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }


    }

    private Order(Builder builder) {
        dateReceived = builder.dateReceived;
        plannedDateStartRepair = builder.plannedDateStartRepair;
        problemDes = builder.problemDes;
        status = builder.status;
        car = builder.car;


        employeeAssigned = builder.employeeAssigned;
        dateStartRepair = builder.dateStartRepair;
        resolutionDes = builder.resolutionDes;
        costRepair = builder.costRepair;
        costOfItems = builder.costOfItems;
        costOfHour = builder.costOfHour;
        workHours = builder.workHours;
    }


}
