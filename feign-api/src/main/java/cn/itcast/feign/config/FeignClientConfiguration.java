package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Name: FeignClientConfiguration
 * @Description: TODO
 * @Author: codeonce
 * @Date: 2022/6/1
 */


public class FeignClientConfiguration {

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC;
    }

}
