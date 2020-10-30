package pl.pawel.cqrs.domain;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf;
import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;

@RequiredArgsConstructor
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final EventStore eventStore;
    private final QueryGateway queryGateway;

    @Override
    public List<User> findAll() {
        return queryGateway.query(new FindAllUsersQuery(), multipleInstancesOf(User.class)).join();
    }

    @Override
    public User findById(int id) {
        return queryGateway.query(new FindAllUsersQuery(), instanceOf(User.class)).join();
    }
    
    public List<?> findEventsFor(int userId){
        return eventStore.readEvents(valueOf(userId))
                .asStream()
                .map(Message::getPayload)
                .collect(toList());
    }
}
