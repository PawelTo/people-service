package pl.pawel.cqrs.domain;

import lombok.Data;

@Data
public class UserChangedNameEvent {

    private final String userId;
    private final String name;
    private final String surname;
}
