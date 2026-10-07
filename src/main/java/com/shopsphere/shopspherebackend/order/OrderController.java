package com.shopsphere.shopspherebackend.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://shopsphere-tau-three.vercel.app"
})
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PLACED");
        }

        return orderRepository.save(order);
    }

    // Update order status
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }

    // Delete order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {

        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }

        orderRepository.deleteById(id);
    }
}