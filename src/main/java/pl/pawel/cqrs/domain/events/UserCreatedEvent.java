package pl.pawel.cqrs.domain.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCreatedEvent {

    private final String userId;
    private final String name;
}
