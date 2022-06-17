package cn.itcast.order;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.config.FeignClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @EnableFeignClients
@EnableFeignClients(clients = UserClient.class, defaultConfiguration = FeignClientConfiguration.class)
@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建RestTemplate并注入容器
     */
    // @Bean
    // @LoadBalanced // Eureka 负载均衡
    // public RestTemplate restTemplate() {
    //     return new RestTemplate();
    // }


    /**
     * 负载均衡策略
     */
    // @Bean
    // public IRule randomRule() {
    //     return new RandomRule();
    // }

}