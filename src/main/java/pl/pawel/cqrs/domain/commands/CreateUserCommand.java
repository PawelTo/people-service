package pl.pawel.cqrs.domain.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String name;
}
