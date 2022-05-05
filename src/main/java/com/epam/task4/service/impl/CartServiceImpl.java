package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.model.entity.Order;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.service.CartService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class CartServiceImpl implements CartService {
    private static final String DEFAULT_SUM_IF_CART_IS_EMPTY = "0.0";

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Map<Long, Integer> getContent() {
        return cartRepository.getAll();
    }

    @Override
    public boolean isEmpty() {
        return cartRepository.getAll().isEmpty();
    }

    @Override
    public List<Commodity> getContentList() {
        return mapListProducts(cartRepository.getAll());
    }

    @Override
    public List<Map.Entry<Commodity, LocalDateTime>> getHistory() {
        return cartRepository.getHistory().entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(productRepository.getById(e.getKey()), e.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Add (put) products id to container and groups its summing their (products) amount
     * accordingly. Also add (put) product id to special container, building a kind
     * of stack-like structure, where the products (their id) are lined up in the order of the "last added".
     *
     * @param id the product id ({@link com.epam.task1.entity.Commodity})
     * @param amount amount (number of products)
     */
    @Override
    public void put(Long id, Integer amount) {
        Map<Long, Integer> cartContainer = cartRepository.getAll();
        if (cartContainer.containsKey(id)) {
            cartRepository.insert(id, cartContainer.get(id) + amount);
        } else {
            cartRepository.insert(id, amount);
        }
        cartRepository.insertInHistory(id, LocalDateTime.now());
    }

    /**
     * Imitation the logic of checkout an order (cart content). The following steps are carried out in the process:
     * <ul>
     *     <li> Create new order in order catalog ({@link OrderCatalog})
     *     <li> output to console total sum of order (total sum the content of cart) and reports the success of the operation
     *     <li> clear the content of cart
     * </ul>
     * <p>
     * Noted: operation cannot be execute if cart is empty
     */
    @Override
    public void checkout() {
        cartRepository.getAll().forEach((key, value) -> { // process amount on stock (mock product catalog)
            Commodity commodity = productRepository.getById(key);
            commodity.setAmount(commodity.getAmount() - value);
        });
        orderRepository.insert(new Order(LocalDateTime.now(), mapListProducts(cartRepository.getAll())));
        cartRepository.getAll().clear();
    }

    /**
     * The sum of the multiply of the content (the price of the product and its quantity)
     * of all pairs of the container.
     * <p>
     * The product price is obtained by finding the corresponding product by its id (key in the cart
     * container from the product catalog ({@link ProductCatalog}).
     * @return total sum of the cart content
     */
    @Override
    public BigDecimal getSum() {
        return cartRepository.getAll().entrySet().stream()
                .map(entry -> productRepository.getById(entry.getKey()).getPrice()
                        .multiply(new BigDecimal(entry.getValue())))
                .reduce(new BigDecimal(DEFAULT_SUM_IF_CART_IS_EMPTY), BigDecimal::add);
    }

    public List<Commodity> mapListProducts(Map<Long, Integer> container) {
        return container.entrySet().stream()
                .map(entry -> {
                    Commodity productCatalogCommodity = productRepository.getById(entry.getKey());
                    Commodity cartCommodity = null;
                    try {
                        cartCommodity = (Commodity) productCatalogCommodity.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    Objects.requireNonNull(cartCommodity);
                    cartCommodity.setAmount(entry.getValue());
                    return cartCommodity;
                })
                .collect(Collectors.toList());
    }
}
