package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MenuRepo;
import com.company.dabawalla.dao.OrderRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Menu;
import com.company.dabawalla.entities.Orders;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/request-tiffin/{menuId}")
    public ResponseEntity<String> requestTiffin(Model model, @PathVariable("menuId") int menuId, HttpSession session){
        try{
            System.out.println(menuId);
            Menu menu = this.menuRepo.findByMenuId(menuId);
            int userid = (Integer) session.getAttribute("user");
            Optional<Customer> optionalUser = this.userRepo.findById(userid);
            Orders order = getOrders(optionalUser, menu);
            this.orderRepo.save(order);
            return ResponseEntity.ok("order placed");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }

    private static Orders getOrders(Optional<Customer> optionalUser, Menu menu) {
        Customer user = optionalUser.get();
        Orders order = new Orders();
        order.setOrderAmount(menu.getMenuPrice());
        order.setOrderDate(new Date());
        order.setOrderPaymentStatus("pending");
        order.setOrderRating("pending");
        order.setOrderFeedback("pending");
        order.setCustomer(user);
        System.out.println(menu.getMess());
        order.setMess(menu.getMess());
        order.setOrderStatus("pending");
        return order;
    }


    @RequestMapping("/accept-order/{orderId}")
    public ResponseEntity<String> acceptOrder(@PathVariable("orderId") int orderId){
        try{
            Orders order = this.orderRepo.findByOrderId(orderId);
            order.setOrderStatus("accepted");
            this.orderRepo.save(order);
            return ResponseEntity.ok("order accepted");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping("/reject-order/{orderId}")
    public ResponseEntity<String> rejectOrder(@PathVariable("orderId") int orderId){
        try{
            Orders order = this.orderRepo.findByOrderId(orderId);
            order.setOrderStatus("rejected");
            this.orderRepo.save(order);
            return ResponseEntity.ok("order rejected");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }


}
