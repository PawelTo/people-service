package pl.pawel.cqrs.domain;

import lombok.Data;

@Data
public class UserChangedOrganizationEvent {

    private final String userId;
    private final User.Organization organization;
}
