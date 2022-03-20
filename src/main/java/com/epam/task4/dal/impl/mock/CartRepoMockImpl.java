package com.epam.task4.dal.impl.mock;

import com.epam.task4.dal.CartRepository;
import com.epam.task4.mockdata.MockCart;

import java.util.Map;

public class CartRepoMockImpl implements CartRepository {
    private MockCart cart = MockCart.getInstance();

    @Override
    public Map<Long, Integer> getAll() {
        return cart.getContainer();
    }

    @Override
    public int insert(Long id, Integer amount) {
        return cart.getContainer().put(id, amount) != null ? 1 : 0;
    }
}
