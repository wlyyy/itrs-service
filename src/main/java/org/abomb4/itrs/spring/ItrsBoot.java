package org.abomb4.itrs.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = "org.abomb4.itrs")
public class ItrsBoot {

    public static void main(String[] args) {
        SpringApplication.run(ItrsBoot.class);
    }
}
