package io.github.emanuelcerqueira.cdc.order;

import io.github.emanuelcerqueira.cdc.common.AbstractService;
import io.github.emanuelcerqueira.cdc.common.exceptions.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order, OrderRepository> {

    @Override
    public Order save(Order order) {

        if (!order.isClientTotalEqualToServerTotal()) {
            throw new BusinessException("The total calculated on the server mismatches the client total");
        }

        return super.save(order);
    }
}
