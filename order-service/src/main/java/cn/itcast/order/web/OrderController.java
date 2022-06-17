package cn.itcast.order.web;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClient userClient;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 1.根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        // 2.使用 Feign 远程调用
        User user = userClient.findById(order.getUserId());
        // 3.封装user到order
        order.setUser(user);
        // 4.返回数据
        return order;
    }


   /*@Autowired
   private RestTemplate restTemplate;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 1.根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        // 2.利用RestTemplate发起http请求，查询用户
        // 定义请求路径
        String url = "http://userservice/user/" + order.getUserId();
        // 发起get请求
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装user到order
        order.setUser(user);
        // 4.返回数据
        return order;
    }*/

}
