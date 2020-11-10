package pl.pawel.cqrs.domain;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import pl.pawel.cqrs.domain.commands.ChangeOrganizationCommand;
import pl.pawel.cqrs.domain.commands.ChangeUserNameCommand;
import pl.pawel.cqrs.domain.commands.CreateUserCommand;
import pl.pawel.cqrs.domain.events.UserChangedNameEvent;
import pl.pawel.cqrs.domain.events.UserChangedOrganizationEvent;
import pl.pawel.cqrs.domain.events.UserCreatedEvent;

import static java.lang.String.valueOf;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String organization;
    private String surname;

    protected UserAggregate() {}

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand) {
        log.info("Command handler - createUserCommand");
        apply(new UserCreatedEvent(createUserCommand.getUserId(), createUserCommand.getName()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        log.info("Event Sourcing handler - userCreatedEvent");
        id = userCreatedEvent.getUserId();
        name = userCreatedEvent.getName();
    }

    @CommandHandler
    public void handle(ChangeUserNameCommand changeUserNameCommand) {
        log.info("Command handler - changeUserNameCommand");
        apply(new UserChangedNameEvent(changeUserNameCommand.getUserId(), changeUserNameCommand.getName(), changeUserNameCommand.getSurname()));
    }

    @EventSourcingHandler
    public void on(UserChangedNameEvent userChangedNameEvent) {
        log.info("Event Sourcing handler - userChangeNameEvent");
        name = userChangedNameEvent.getName();
        surname = userChangedNameEvent.getSurname();
    }

    @CommandHandler
    public void handle(ChangeOrganizationCommand changeOrganizationCommand) {
        log.info("Command handler - changeOrganizationCommand");
        apply(new UserChangedOrganizationEvent(changeOrganizationCommand.getUserId(), changeOrganizationCommand.getOrganization()));
    }

    @EventSourcingHandler
    public void on(UserChangedOrganizationEvent userChangedOrganizationEvent) {
        log.info("Event Sourcing handler - userChangedOrganizationEvent");
        organization = valueOf(userChangedOrganizationEvent.getOrganization());
    }
}