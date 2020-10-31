package pl.pawel.cqrs.domain.events;

import lombok.Data;
import pl.pawel.cqrs.domain.User;

@Data
public class UserChangedOrganizationEvent {

    private final String userId;
    private final User.Organization organization;
}
