package model;

import checkers.units.quals.C;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private final Date birthDate;

        public Builder(String firstName, String lastName, Date birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

        public Client build() {
            return new Client(this);
        }
    }

    private Client(Builder builder){
        firstName = builder.firstName;
        lastName = builder.lastName;
        birthDate = builder.birthDate;
    }
}
