package pl.pawel.cqrs.controllers.view;

import lombok.Data;

@Data
public class PersonView {

    private String address;
    private int age;
    private String name;
    private String surname;
}
