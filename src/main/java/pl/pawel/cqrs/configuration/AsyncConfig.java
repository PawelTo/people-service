package pl.pawel.cqrs.configuration;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

  @PostConstruct
  private void postConstruct(){
    log.info("Enabling asynchronous processing");
  }
}
