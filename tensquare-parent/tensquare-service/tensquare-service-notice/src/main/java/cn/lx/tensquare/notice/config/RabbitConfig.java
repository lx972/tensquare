package cn.lx.tensquare.notice.config;

import cn.lx.tensquare.notice.mq.SysNoticeListener;
import cn.lx.tensquare.notice.mq.UserNoticeListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 *
 * @Author Administrator
 * @date 17:58
 */
@Configuration
public class RabbitConfig {

    /**
     * 创建一个容器，设置自定义的监听器
     * 作者发布新文章
     * @param connectionFactory
     * @return
     */
    @Bean("sysNoticeContainer")
    public SimpleMessageListenerContainer createSysNoticeContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerContainer =
                new SimpleMessageListenerContainer(connectionFactory);
        //使用channel
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        //设置自己编写的监听器
        simpleMessageListenerContainer.setMessageListener(new SysNoticeListener());
        return simpleMessageListenerContainer;
    }


    /**
     * 创建一个容器，设置自定义的监听器
     * 用户对文章点赞
     * @param connectionFactory
     * @return
     */
    @Bean("userNoticeContainer")
    public SimpleMessageListenerContainer createUserNoticeContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerContainer =
                new SimpleMessageListenerContainer(connectionFactory);
        //使用channel
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        //设置自己编写的监听器
        simpleMessageListenerContainer.setMessageListener(new UserNoticeListener());
        return simpleMessageListenerContainer;
    }

}
