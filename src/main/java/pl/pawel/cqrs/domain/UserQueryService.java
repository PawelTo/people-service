package pl.pawel.cqrs.domain;

import java.util.List;

public interface UserQueryService {

    List<User> findAll();

    User findById(int id);

    List<?> findEventsFor(int userId);
}
