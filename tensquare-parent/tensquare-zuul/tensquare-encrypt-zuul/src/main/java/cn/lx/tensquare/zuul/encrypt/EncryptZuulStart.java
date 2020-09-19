package cn.lx.tensquare.zuul.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * cn.lx.tensquare
 *
 * @Author Administrator
 * @date 15:35
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EncryptZuulStart {

    public static void main(String[] args) {
        SpringApplication.run(EncryptZuulStart.class, args);
    }

}
