package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne
 * Order
 * Order -> Member
 * Order -> Member -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiConroller {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderQueryDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderQueryDto> collect = orders.stream()
                .map(o -> new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderQueryDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderQueryDto> collect = orders.stream()
                .map(o -> new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());
        return collect;
    }
    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }
}
