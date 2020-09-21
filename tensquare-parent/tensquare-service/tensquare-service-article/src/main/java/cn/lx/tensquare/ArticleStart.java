package cn.lx.tensquare;

import cn.lx.tensquare.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


/**
 * cn.lx.tensquare
 *
 * @Author Administrator
 * @date 15:35
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"cn.lx.tensquare.notice.feign"})
@MapperScan("cn.lx.tensquare.article.dao")
public class ArticleStart {

    public static void main(String[] args) {
        SpringApplication.run(ArticleStart.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
