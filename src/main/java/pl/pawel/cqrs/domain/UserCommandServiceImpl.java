package pl.pawel.cqrs.domain;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import pl.pawel.cqrs.domain.commands.CreateUserCommand;

import java.util.concurrent.CompletableFuture;

import static java.lang.String.valueOf;

@RequiredArgsConstructor
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<Object> create(User user) {
        return commandGateway.send(new CreateUserCommand(valueOf(user.getId()), user.getName()));
    }
}
