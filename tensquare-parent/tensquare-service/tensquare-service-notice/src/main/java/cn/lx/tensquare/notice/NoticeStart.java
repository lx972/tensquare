package cn.lx.tensquare.notice;

import cn.lx.tensquare.notice.netty.NettyServer;
import cn.lx.tensquare.utils.ApplicationContextProvider;
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
@SpringBootApplication(scanBasePackages = {"cn.lx.tensquare.notice","cn.lx.tensquare.utils"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"cn.lx.tensquare.user.feign","cn.lx.tensquare.article.feign","cn.lx.tensquare.comment.feign"})
@MapperScan("cn.lx.tensquare.notice.dao")
public class NoticeStart {

    public static void main(String[] args) {
        SpringApplication.run(NoticeStart.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }


}
