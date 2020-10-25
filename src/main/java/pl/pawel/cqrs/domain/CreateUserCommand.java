package pl.pawel.cqrs.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String name;

}
