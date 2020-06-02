package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkingHoursModel {
    //    firstName, lastName, hourlyCost, SUM(o.workHours) AS sum, hourlyCost * SUM(o.workHours) AS total_cost
    private String firstName;
    private String lastName;
    private double hourlyCost;
    private double workHours;
    private double totalCost;

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private final double hourlyCost;
        private final double workHours;
        private final double totalCost;

        public Builder(String firstName, String lastName, double hourlyCost, double workHours, double totalCost) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.hourlyCost = hourlyCost;
            this.workHours = workHours;
            this.totalCost = totalCost;
        }

        public WorkingHoursModel build() {
            return new WorkingHoursModel(this);
        }
    }

    public WorkingHoursModel(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        hourlyCost = builder.hourlyCost;
        workHours = builder.workHours;
        totalCost = builder.totalCost;

    }
}
