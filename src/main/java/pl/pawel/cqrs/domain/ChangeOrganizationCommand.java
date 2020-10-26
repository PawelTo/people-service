package pl.pawel.cqrs.domain;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeOrganizationCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final User.Organization organization;
}
