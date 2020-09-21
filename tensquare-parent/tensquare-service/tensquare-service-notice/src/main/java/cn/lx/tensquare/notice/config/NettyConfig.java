package cn.lx.tensquare.notice.config;

import cn.lx.tensquare.notice.netty.NettyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类负责启动netty
 *
 * @Author Administrator
 * @date 17:11
 */
@Configuration
public class NettyConfig {

    @Bean("nettyServer")
    public NettyServer createNettyServer(){
        NettyServer nettyServer = new NettyServer();
        //开启一个线程为netty服务
        new Thread(){
            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             * @see #Thread(ThreadGroup, Runnable, String)
             */
            @Override
            public void run() {
                //启动netty
                nettyServer.start(1234);
            }
        }.start();

        return nettyServer;
    }
}
