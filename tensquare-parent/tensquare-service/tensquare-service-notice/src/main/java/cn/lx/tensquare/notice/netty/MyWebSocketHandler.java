package cn.lx.tensquare.notice.netty;

import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.entity.StatusCode;
import cn.lx.tensquare.utils.ApplicationContextProvider;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 流水线上的一个工人
 * 自定义处理
 *
 * @Author Administrator
 * @date 17:15
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    SimpleMessageListenerContainer sysNoticeContainer= (SimpleMessageListenerContainer)
            ApplicationContextProvider.getApplicationContext().getBean("sysNoticeContainer");

    SimpleMessageListenerContainer userNoticeContainer= (SimpleMessageListenerContainer)
            ApplicationContextProvider.getApplicationContext().getBean("userNoticeContainer");

    /**
     * 获取rabbit的操作对象
     */
    RabbitTemplate rabbitTemplate = ApplicationContextProvider
            .getApplicationContext().getBean(RabbitTemplate.class);

    /**
     * 存放websocket连接的map，key为用户id
     */
    public static ConcurrentHashMap<String, Channel> userChannelMap=new ConcurrentHashMap<>();

    /**
     * 用户请求websocket服务端执行的方法(用户主动）
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                TextWebSocketFrame textWebSocketFrame) throws Exception {

        //约定用户第一次请求携带的数据：{"userId":"1"}
        //获取通信的数据
        String json = textWebSocketFrame.text();
        Map<String,String> parseObject = JSON.parseObject(json, Map.class);
        String userId = parseObject.get("userId");
        //从map中取出连接
        Channel channel = userChannelMap.get(userId);
        if (channel==null){
            //第一次请求的时候需要建立websocket连接
            channel = channelHandlerContext.channel();
            //放入map中
            userChannelMap.put(userId,channel);
        }
        //不是第一次请求，已有websocket连接
        /**
         * 只用于完成新消息提醒，只获取消息数量
         */
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        //拼接获取队列的名称
        String sysQueueName="author_subscribe_"+userId;
        //获取队列信息
        Properties sysQueueProperties = rabbitAdmin.getQueueProperties(sysQueueName);
        //获取消息的数量
        int sysNoticeCount=0;
        if (sysQueueProperties!=null){
            //获取消息数量
            sysNoticeCount= (int) sysQueueProperties.get("QUEUE_MESSAGE_COUNT");
        }

        //拼接获取队列的名称
        String userQueueName="article_thumbup_"+userId;
        //获取队列信息
        Properties userQueueProperties = rabbitAdmin.getQueueProperties(userQueueName);
        //获取消息的数量
        int userNoticeCount=0;
        if (userQueueProperties!=null){
            //获取消息数量
            userNoticeCount= (int) userQueueProperties.get("QUEUE_MESSAGE_COUNT");
        }

        //将消息数量推送到客户端
        Map<String,Integer> data=new HashMap<>();
        data.put("sysNoticeCount",sysNoticeCount);
        data.put("userNoticeCount",userNoticeCount);
        Result<Map<String, Integer>> result = new Result<>(true, StatusCode.OK, "获取消息数量成功", data);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(result)));

        //清空队列中的消息，防止再次消费
        if(sysNoticeCount>0){
            rabbitAdmin.purgeQueue(sysQueueName);
        }
        if(userNoticeCount>0){
            rabbitAdmin.purgeQueue(userQueueName);
        }
        //为用户的消息通知队列注册监听器，便于用户在线的时候，
        //一旦有消息，可以主动推送给用户，不需要用户请求服务器获取数据
        sysNoticeContainer.addQueueNames(sysQueueName);
        userNoticeContainer.addQueueNames(userQueueName);
    }
}
