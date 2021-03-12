package io.github.emanuelcerqueira.cdc.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        OrderResponse response = orderMapper.mapToOrderResponse(order);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderMapper.mapToOrder(orderRequest);
        Order persistedOrder = orderService.save(order);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(persistedOrder.getId()).toUri();
        OrderResponse response = orderMapper.mapToOrderResponse(persistedOrder);

        return ResponseEntity.created(uri).body(response);
    }

}
