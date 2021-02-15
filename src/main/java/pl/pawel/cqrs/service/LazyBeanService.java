package pl.pawel.cqrs.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Slf4j
public class LazyBeanService {

  @PostConstruct
  private void postConstruct(){
    log.info("Created LazyBeanService");
  }

  public String call(){
    return "component to learn Lazy bean initialization";
  }
}
