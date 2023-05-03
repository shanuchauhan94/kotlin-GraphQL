package com.spring.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.client.HttpGraphQlClient
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BeanConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().baseUrl("http://127.0.0.1:9091/graphql")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }

    /* fun webClient(builder: WebClient.Builder): WebClient =
         builder.baseUrl("http://127.0.0.1:9091/graphql")
             .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
             .build()*/

    @Bean
    fun graphQlClient(): HttpGraphQlClient {
        return HttpGraphQlClient.builder(this.webClient())
            .build()
    }

}