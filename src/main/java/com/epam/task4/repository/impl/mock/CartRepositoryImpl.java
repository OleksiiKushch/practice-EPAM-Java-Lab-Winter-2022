package com.epam.task4.repository.impl.mock;

import com.epam.task4.repository.CartRepository;
import com.epam.task4.model.data_sources.Cart;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
public class CartRepositoryImpl implements CartRepository {
    private final Cart cart;

    public CartRepositoryImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Map<Long, Integer> getAll() {
        return cart.getContainer();
    }

    @Override
    public int insert(Long id, Integer amount) {
        return cart.getContainer().put(id, amount) != null ? 1 : 0;
    }

    @Override
    public Map<Long, LocalDateTime> getHistory() {
        return cart.getHistory();
    }

    @Override
    public int insertInHistory(Long id, LocalDateTime dateTime) {
        return cart.getHistory().put(id, dateTime) != null ? 1 : 0;
    }
}
