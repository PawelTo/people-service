package pl.pawel.cqrs.domain;

import java.util.concurrent.CompletableFuture;

public interface UserCommandService {

    public CompletableFuture<Object> create(User user);
}
