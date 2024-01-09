package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Integer> {
// create a query to find all orders by order status and mess id
    @Query("from Orders as o where o.orderStatus =:orderStatus and o.mess.messId =:mess")
    public List<Orders> findByOrderStatusAndMessId(String orderStatus, int mess);

    public Orders findByOrderId(int orderId);
}
