package cn.lx.tensquare.gateway.encrypt.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * cn.lx.tensquare.gateway.encrypt.filter
 *
 * @Author Administrator
 * @date 18:34
 */
public class AuthorizeFilter implements GlobalFilter, Ordered {

    /**
     * 此处写需要过滤的条件
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
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
