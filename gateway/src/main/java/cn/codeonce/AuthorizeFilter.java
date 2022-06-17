package cn.codeonce;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * @Name: AuthorizeFilter
 * @Description: TODO
 * @Author: codeonce
 * @Date: 2022/6/2
 */

// @Order(-1) // 设置过滤器权重
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    /**
     * 处理当前请求，有必要的话通过{ @link GatewayFilterChain }将请求交给下一个过滤器处理
     *
     * @param exchange 请求上下文，里面可以获取 Request、Response 等新车型
     * @param chain    用来把请求委托给下一个过滤器
     * @return { @code Mono<Void> } 返回标示当前过滤器业务结束
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();

        // 2.获取参数中的 authorize 参数
        String authorize = params.getFirst("authorization");

        // 3.判断参数值是否等于 admin
        if ("admin".equals(authorize)) {
            // 3.1.相等 -》 放行
            return chain.filter(exchange);
        }
        // 3.2.不等 -》 拦截
        // 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 拦截请求
        return exchange.getResponse().setComplete();

    }

    @Override
    public int getOrder() {
        return -1;
    }
}
