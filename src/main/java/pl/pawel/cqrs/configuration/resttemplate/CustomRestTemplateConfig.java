package pl.pawel.cqrs.configuration.resttemplate;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class CustomRestTemplateConfig {

  private final HttpClient httpClient;

  @Bean
  public RestTemplate customRestTemplate(AuthorizationHeaderInterceptor authorizationHeaderInterceptor){
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(clientHttpRequestFactory());
    restTemplate.getInterceptors().add(authorizationHeaderInterceptor);
    restTemplate.setErrorHandler(new ResponseErrorHandler());
    return restTemplate;
  }

  @Bean
  public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setHttpClient(httpClient);
    return clientHttpRequestFactory;
  }
}
