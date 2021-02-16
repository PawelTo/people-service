package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.cqrs.service.AsynchService;

/**
 * Class for learning asynchronous computations
 */
@RequestMapping(V1AsynchController.API_PATH)
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = V1AsynchController.API_PATH)
public class V1AsynchController {

  public static final String API_PATH = "/api/v1/asynch";

  private final AsynchService asynchService;

  @GetMapping
  public String processAsynch(){
    log.info("start V1AsynchController.processAsynch");
    try {
      asynchService.asynchProcess();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("after call asynchService.asynchProcess()");
    return "process ready";
  }
}
