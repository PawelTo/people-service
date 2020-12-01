package pl.pawel.cqrs.controllers.view;

import lombok.Builder;
import lombok.Data;
import pl.pawel.cqrs.persistence.entity.PersonEntity;

import static java.time.LocalDate.now;
import static java.time.Period.between;

@Builder
@Data
public class PersonView {

    private String address;
    private int age;
    private String name;
    private int salary;
    private String surname;

    public static PersonView from(PersonEntity personEntity) {
        return PersonView.builder()
                         .address(personEntity.getAddress())
                         .age(between(personEntity.getPersonKey().getDateOfBirth(), now()).getYears())
                         .name(personEntity.getName())
                         .salary(personEntity.getSalary())
                         .surname(personEntity.getPersonKey().getSurname())
                         .build();
    }
}
