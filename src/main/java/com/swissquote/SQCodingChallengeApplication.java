package com.swissquote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SQCodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SQCodingChallengeApplication.class, args);
    }
}
