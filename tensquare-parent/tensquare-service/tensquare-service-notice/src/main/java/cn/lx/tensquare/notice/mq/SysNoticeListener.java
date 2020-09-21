package cn.lx.tensquare.notice.mq;

import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.entity.StatusCode;
import cn.lx.tensquare.notice.netty.MyWebSocketHandler;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.util.HashMap;
import java.util.Map;

/**
 * mq的消息监听
 * 作者发布新文章
 * @Author Administrator
 * @date 18:00
 */
public class SysNoticeListener implements ChannelAwareMessageListener {


    /**
     * 当该监听器被注册到队列上时，如果有消息到达会触发该方法
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        //通过队列名获取用户id
        String consumerQueue = message.getMessageProperties().getConsumerQueue();
        String userId = consumerQueue.substring(consumerQueue.lastIndexOf("_") + 1);

        //根据用户id获取用户的连接
        io.netty.channel.Channel channel1 = MyWebSocketHandler.userChannelMap.get(userId);
        //判断用户是否在线
        if (channel1!=null){
            //将消息数量推送到客户端
            Map<String,Integer> data=new HashMap<>();
            data.put("sysNoticeCount",1);
            Result<Map<String, Integer>> result = new Result<>(true, StatusCode.OK, "获取消息数量成功", data);
            channel1.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(result)));
        }
    }
}
