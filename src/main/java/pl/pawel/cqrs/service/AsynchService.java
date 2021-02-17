package pl.pawel.cqrs.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AsynchService {

  private final ExecutorService executorService;

  @Async
  public CompletableFuture<String> asyncProcess() throws InterruptedException {
    log.info("start asynchService.asyncProcess");
    Thread.sleep(3000L);
    log.info("after sleeping thread");
    return CompletableFuture.supplyAsync(() -> {
      log.info("start method in supply Async");
      return "values";
    });
  }

  public CompletableFuture<String> asyncProcess2Implementation(){
    log.info("start asynchService.asyncProcess2Implementation");
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("after sleeping thread2");
      log.info("start method in supply Async2");
      return "values2";
    }, executorService);
  }
}
