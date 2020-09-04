package cn.lx.tensquare.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * cn.lx.tensquare.eureka
 *
 * @Author Administrator
 * @date 15:43
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka1Start {
    public static void main(String[] args) {
        SpringApplication.run(Eureka1Start.class, args);
    }

}
