package pl.pawel.cqrs.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCreatedEvent {

    private final String userId;
    private final String name;
}
