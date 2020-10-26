package pl.pawel.cqrs.domain;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.valueOf;

@Service
public class UserEventHandler {
    private final Map<String, User> userMap = new HashMap<>();

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        String userId = userCreatedEvent.getUserId();
        userMap.put(userId, new User(valueOf(userId), userCreatedEvent.getName(), null, null));
    }

    @EventHandler
    public void on (UserChangedNameEvent userChangedNameEvent){
        String  userId = userChangedNameEvent.getUserId();
        User user = userMap.get(userId);
        user.setName(userChangedNameEvent.getName());
        user.setSurname(userChangedNameEvent.getSurname());
        userMap.replace(userId,user);
    }

    @EventHandler
    public void on (UserChangedOrganizationEvent userChangedOrganizationEvent){
        String  userId = userChangedOrganizationEvent.getUserId();
        User user = userMap.get(userId);
        user.setOrganization(userChangedOrganizationEvent.getOrganization());
        userMap.replace(userId,user);
    }

    @QueryHandler
    public List<User> handle (FindAllUsersQuery findAllUsersQuery){
        return new ArrayList<>(userMap.values());
    }
}
