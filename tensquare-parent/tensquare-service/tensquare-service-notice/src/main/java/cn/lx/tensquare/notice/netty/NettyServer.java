package cn.lx.tensquare.notice.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * netty的服务端
 *
 * @Author Administrator
 * @date 16:58
 */
public class NettyServer {

    public void start(int port){
        System.out.println("netty开始启动。。。。");
        //创建一个用于接收新连接的对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //用于接收新连接
        NioEventLoopGroup boos = new NioEventLoopGroup();
        //用于操作连接的数据
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //开始一个服务
        serverBootstrap
                .group(boos,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        //请求消息解码器
                        channel.pipeline().addLast(new HttpServerCodec());
                        //将多个消息转换为单一的request或者response对象
                        channel.pipeline().addLast(new HttpObjectAggregator(65536));
                        //处理websocket的消息事件
                        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                        //自定义一个工人
                        MyWebSocketHandler myWebSocketHandler = new MyWebSocketHandler();
                        channel.pipeline().addLast(myWebSocketHandler);
                    }
                }).bind(port);
    }
}
