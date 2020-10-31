package pl.pawel.cqrs.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.pawel.cqrs.domain.User;

@Data
public class ChangeOrganizationCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final User.Organization organization;
}
