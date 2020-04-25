package model;

import lombok.*;

@Data
public class Employee {
    private int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final Integer telNumber;
    private final String note;
    @NonNull
    private final long hourlyCost;

    public static class Builder{
        private final String firstName;
        private final String lastName;
        private final long hourlyCost;

        private String address = "domyślny";
        private String note = "opis";
        private Integer telNumber = 48;



        public Builder(String firstName, String lastName, long hourlyCost) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.hourlyCost = hourlyCost;
        }
        public Builder address(String val){
            address = val;
            return this;
        }
        public Builder note(String val){
            note = val;
            return this;
        }
        public Builder telNumber(Integer val){
            telNumber = val;
            return this;
        }
     /*   public Builder id(Integer val){
            id = val;
            return this;
        }*/


        public Employee build(){
            return new Employee(this);
        }
    }

    private Employee(Builder builder){
        firstName = builder.firstName;
        lastName = builder.lastName;
        hourlyCost = builder.hourlyCost;
        note = builder.note;
        address = builder.address;
        telNumber = builder.telNumber;
//        id = builder.id;

    }



}
