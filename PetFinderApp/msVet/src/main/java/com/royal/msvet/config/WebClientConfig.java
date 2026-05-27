package com.royal.msvet.config;

import io.netty.channel.ChannelOption;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("petWebClient")
    public WebClient petWebClient(WebClient.Builder builder, MsVetClientProperties properties) {
        return buildClient(builder, properties.pet());
    }

    @Bean
    @Qualifier("notificationWebClient")
    public WebClient notificationWebClient(WebClient.Builder builder, MsVetClientProperties properties) {
        return buildClient(builder, properties.notification());
    }

    @Bean
    @Qualifier("shelterWebClient")
    public WebClient shelterWebClient(WebClient.Builder builder, MsVetClientProperties properties) {
        return buildClient(builder, properties.shelter());
    }

    @Bean
    @Qualifier("adopterWebClient")
    public WebClient adopterWebClient(WebClient.Builder builder, MsVetClientProperties properties) {
        return buildClient(builder, properties.adopter());
    }

    private WebClient buildClient(WebClient.Builder builder, MsVetClientProperties.Service service) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, service.connectTimeoutMillis())
                .responseTimeout(Duration.ofMillis(service.responseTimeoutMillis()));

        return builder
                .baseUrl(service.baseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
