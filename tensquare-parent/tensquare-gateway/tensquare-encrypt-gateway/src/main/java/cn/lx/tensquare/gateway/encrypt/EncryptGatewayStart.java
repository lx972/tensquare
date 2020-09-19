package cn.lx.tensquare.gateway.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * cn.lx.tensquare
 *
 * @Author Administrator
 * @date 15:35
 */
@SpringBootApplication
@EnableEurekaClient
public class EncryptGatewayStart {

    public static void main(String[] args) {
        SpringApplication.run(EncryptGatewayStart.class, args);
    }

}
