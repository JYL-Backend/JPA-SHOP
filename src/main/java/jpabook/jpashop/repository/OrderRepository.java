package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private  final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOwn(Long orderId){
        return em.find(Order.class, orderId);
    }

    public List<Order> findAllByString(OrderSearch orderSearch){
        return em.createQuery("select o from Order o join o.member m " +
                " where o.status=:status " +
                " and m.name like :name", Order.class)
                .setParameter("name", orderSearch.getMemberName())
                .setParameter("status", orderSearch.getOrderStatus())
                .getResultList();
    }
}
