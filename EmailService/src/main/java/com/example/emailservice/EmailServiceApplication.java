package com.example.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class EmailServiceApplication {

    public static void main(String[] args) {
        System.setProperty("http.proxyHost", "host");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1|10.*.*.*");
        System.setProperty("https.proxyHost", "host");
        System.setProperty("https.proxyPort", "8080");
        System.setProperty("https.nonProxyHosts", "localhost|127.0.0.1|10.*.*.*");
        System.setProperty("java.net.useSystemProxies", "true");
        SpringApplication.run(EmailServiceApplication.class, args);
    }


}
