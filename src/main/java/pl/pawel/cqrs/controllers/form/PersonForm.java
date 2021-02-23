package pl.pawel.cqrs.controllers.form;

import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawel.cqrs.controllers.form.validation.constraint.NoFutureDate;
import pl.pawel.cqrs.persistence.entity.PersonEntity;

import java.time.LocalDate;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@NoFutureDate
public class PersonForm {

    private String address;
    private LocalDate dateOfBirth;
    private String name;
    private int salary;
    private String surname;

    public PersonEntity toPersonEntity() {
        PersonEntity.PersonKey.builder()
                .dateOfBirth(dateOfBirth)
                .surname(surname)
                .build();

        return PersonEntity.builder()
                           .personKey(PersonEntity.PersonKey.builder()
                                                            .dateOfBirth(dateOfBirth)
                                                            .surname(surname)
                                                            .build())
                           .address(address)
                           .name(name)
                           .salary(salary)
                           .build();
    }
}
