package pl.pawel.cqrs.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String organization;
    private String surname;

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        AggregateLifecycle.apply(new UserCreatedEvent(createUserCommand.getUserId(), createUserCommand.getName()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this.id = userCreatedEvent.getUserId();
        name = userCreatedEvent.getName();
    }

    protected UserAggregate(){}
}
