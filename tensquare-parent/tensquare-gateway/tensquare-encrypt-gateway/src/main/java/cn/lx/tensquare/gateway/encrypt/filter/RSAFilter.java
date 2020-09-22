package cn.lx.tensquare.gateway.encrypt.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * cn.lx.tensquare.gateway.encrypt.filter
 *
 * @Author Administrator
 * @date 18:34
 */
@Component
public class RSAFilter implements GlobalFilter, Ordered {

    /**
     * 此处写需要过滤的条件
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //post和put请求获取请求体
        if (!request.getMethod().equals(HttpMethod.GET)&&!request.getMethod().equals(HttpMethod.DELETE)){
            //获取请求体
            DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        try {
                            String bodyString = new String(bytes, "utf-8");
                            System.out.println(bodyString);
                            exchange.getAttributes().put("POST_BODY",bodyString);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest)
                                .build());
                    });


            /*ServerRequest serverRequest = ServerRequest.create(exchange,
                    HandlerStrategies.withDefaults().messageReaders());
            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                    .flatMap(body -> {
                        System.out.println("获取requestBody:...");
                        System.out.println(body);
                        // 头文件有了，requestBody有了，自己的项目怎么鉴权怎么来
                        return Mono.just(body);
                    });*/


            /*Flux<DataBuffer> body = request.getBody();
            AtomicReference<String> stringAtomicReference = new AtomicReference<>();
           body.subscribe(dataBuffer -> {
               CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
               DataBufferUtils.release(dataBuffer);
               stringAtomicReference.set(charBuffer.toString());
           });
            String bodyString = stringAtomicReference.get();

            System.out.println("bodyString:"+bodyString);*/


        }
        return null;
    }

    /**
     * 过滤器的优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
