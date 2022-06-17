package cn.codeonce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Name: GatewayApplication
 * @Description: TODO
 * @Author: codeonce
 * @Date: 2022/6/2
 */

@Slf4j
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("网关启动成功!");
    }
}
