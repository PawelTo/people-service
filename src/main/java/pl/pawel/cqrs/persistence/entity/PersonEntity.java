package pl.pawel.cqrs.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class PersonEntity {

    @EmbeddedId
    private PersonKey personKey;
    private String address;
    private String name;
    private int salary;

    @Data
    @Embeddable
    public static class PersonKey {
        private LocalDate dateOfBirth;
        private String surname;
    }
}
