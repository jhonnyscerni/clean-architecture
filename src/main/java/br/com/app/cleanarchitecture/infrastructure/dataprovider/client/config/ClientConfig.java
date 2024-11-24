package br.com.app.cleanarchitecture.infrastructure.dataprovider.client.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${app.feign.maxConnections}")
    private int maxConnections;

    @Value("${app.feign.maxConnectionsPerRoute}")
    private int maxConnectionsPerRoute;

    @Bean
    public Client feignClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxConnections);
        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new ApacheHttpClient(httpClient);
    }
}