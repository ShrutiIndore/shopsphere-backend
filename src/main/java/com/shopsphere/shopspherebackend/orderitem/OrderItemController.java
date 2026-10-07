package com.shopsphere.shopspherebackend.orderitem;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/order-items")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://shopsphere-tau-three.vercel.app"
})
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get order item by ID
    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
    }

    // Create order item
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Delete order item
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {

        if (!orderItemRepository.existsById(id)) {
            throw new RuntimeException("Order item not found");
        }

        orderItemRepository.deleteById(id);
    }
}
