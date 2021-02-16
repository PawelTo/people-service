package pl.pawel.cqrs.service;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsynchService {

  @Async
  public CompletableFuture<String> asynchProcess() throws InterruptedException {
    log.info("start asynchService.asynchProcess");
    Thread.sleep(10000L);
    log.info("after sleeping thread");
    return CompletableFuture.supplyAsync(() -> {
      log.info("start method in supply Async");
      return "values";
    });
  }
}
