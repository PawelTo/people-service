package pl.pawel.cqrs.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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

  @Bean
  public ExecutorService delegate(){
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 60, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>());
    threadPoolExecutor.allowCoreThreadTimeOut(true);
    return threadPoolExecutor;
  }
/*
  @Bean
  public DelegatingSecurityExecutorService taskExecutor(ExecutorService delegate){
    return new
  }*/
}
