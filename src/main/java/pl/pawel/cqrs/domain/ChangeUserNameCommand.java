package pl.pawel.cqrs.domain;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeUserNameCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String name;
    private final String surname;
}
