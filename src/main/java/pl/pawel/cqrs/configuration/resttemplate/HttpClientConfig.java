package pl.pawel.cqrs.configuration.resttemplate;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

  private static final int CONNECT_TIMEOUT = 60_000;
  private static final int DEFAULT_KEEP_ALIVE = 30_000;
  private static final int MAX_CONNECTIONS = 10;
  private static final int MAX_CONNECTIONS_PER_ROUTE = 10;
  private static final int SOCKET_TIMEOUT = 60_000;

  @Bean
  public CloseableHttpClient httpClient(final PoolingHttpClientConnectionManager connectionManager) {
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(
        SOCKET_TIMEOUT)
        .build();

    ConnectionKeepAliveStrategy keepAliveStrategy = createKeepAliveStrategy();

    return HttpClients.custom().setConnectionManager(connectionManager)
        .setDefaultRequestConfig(requestConfig).setKeepAliveStrategy(keepAliveStrategy).build();
  }

  @Bean
  public PoolingHttpClientConnectionManager connectionManager() {
    final PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
    manager.setMaxTotal(MAX_CONNECTIONS);
    manager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);
    return manager;
  }

  private ConnectionKeepAliveStrategy createKeepAliveStrategy() {
    return (httpResponse, httpContext) -> {
      HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator(
          HTTP.CONN_KEEP_ALIVE));
      while (it.hasNext()) {
        HeaderElement headerElement = it.nextElement();
        String param = headerElement.getName();
        String value = headerElement.getValue();
        if (value != null && param.equalsIgnoreCase("timeout")) {
          return Long.parseLong(value) * 1000;
        }
      }
      return DEFAULT_KEEP_ALIVE;
    };
  }
}
