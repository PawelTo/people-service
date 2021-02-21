package pl.pawel.cqrs.configuration.resttemplate;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderInterceptor implements ClientHttpRequestInterceptor {

  private static final String AUTH_HEADER = "Authorization";
  private static final String AUTH_HEADER_PREFIX = "Bearer ";

  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
      ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    HttpHeaders headers = httpRequest.getHeaders();
    if(!headers.containsKey(AUTH_HEADER)){
      headers.add(AUTH_HEADER, AUTH_HEADER_PREFIX+ getUserAccessToken());
    }
    return clientHttpRequestExecution.execute(httpRequest,bytes);
  }

  private String getUserAccessToken() {

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //auth.getDetails();
    return null;
  }
}
