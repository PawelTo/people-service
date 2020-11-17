package pl.pawel.cqrs.controllers.form;

import lombok.Data;
import pl.pawel.cqrs.persistence.entity.PersonEntity;

import java.time.LocalDate;

@Data
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
