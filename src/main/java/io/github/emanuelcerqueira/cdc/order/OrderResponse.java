package io.github.emanuelcerqueira.cdc.order;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class OrderResponse {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String cpfCnpj;
    private String address;
    private List<CartItemResponse> items;
    private BigDecimal total;
    private BigDecimal discounts;
    private BigDecimal totalFinal;

}
