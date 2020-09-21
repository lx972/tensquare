import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * PACKAGE_NAME
 *
 * @Author Administrator
 * @date 16:28
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        //不接受新的连接，并且是在父通道类中完成一些操作，通常用于客户端中
        Bootstrap bootstrap = new Bootstrap();

        //EventLoopGroup中包含了多个EventLoop实例，用来管理EventLoop的组件
        NioEventLoopGroup group = new NioEventLoopGroup();

        //客户端执行
        bootstrap
                .group(group)
                //对网络套接字的I/O操作，例如读、写、连接、绑定等操作进行适配和封装的组件
                .channel(NioSocketChannel.class)
                //ChannelInitializer 用于对刚创建的channel进行初始化，将ChannelHandler添加到channel的ChannelPipeline处理链路中
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 组件从流水线头部进入，流水线上的工人按顺序对组件进行加工
                        // 流水线相当于ChannelPipeline
                        // 流水线工人相当于ChannelHandler
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        //客户端连接服务端
        Channel channel = bootstrap.connect("127.0.0.1", 9002).channel();
        while (true){
            channel.writeAndFlush("测试数据");
            Thread.sleep(2000);
        }


    }
}
