package pl.pawel.cqrs.configuration.resttemplate;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class ResponseErrorHandler extends DefaultResponseErrorHandler {

  @Override
  protected void handleError(ClientHttpResponse response, HttpStatus statusCode)
      throws IOException {
    switch (statusCode){
      case UNPROCESSABLE_ENTITY:
        throw new RuntimeException(getBody(response));
    }
  super.handleError(response, statusCode);
  }

  private String getBody(ClientHttpResponse response) throws IOException {
    return response.getBody().toString();
  }
}
