package cn.lx.tensquare.user;

import cn.lx.tensquare.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


/**
 * cn.lx.tensquare
 *
 * @Author Administrator
 * @date 15:35
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.lx.tensquare.user.dao")
public class UserStart {

    public static void main(String[] args) {
        SpringApplication.run(UserStart.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
