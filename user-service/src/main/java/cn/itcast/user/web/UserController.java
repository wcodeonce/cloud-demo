package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@RestController
// @RefreshScope   // Nacos配置热更新
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 注入nacos中的配置属性
    // @Value("${pattern.dateformat}")
    // private String dataformat;

    @Autowired
    private PatternProperties properties;

    /**
     * 编写controller，通过日期格式器返回现在的时间返回
     */
    @GetMapping("/now")
    public String now(@RequestHeader(value = "Truth", required = false) String Truth) {
        log.info("Truth => {}", Truth);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat(), Locale.CHINA));
    }

    @GetMapping("/prop")
    public PatternProperties prop() {
        return properties;
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
