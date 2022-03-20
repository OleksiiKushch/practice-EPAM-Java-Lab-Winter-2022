package com.epam.task4.dal.impl.mock;

import com.epam.task4.dal.CartHistoryRepository;
import com.epam.task4.mockdata.MockCartHistory;

import java.util.Map;

public class CartHistoryRepoMockImpl implements CartHistoryRepository {
    private MockCartHistory cartHistory = MockCartHistory.getInstance();

    @Override
    public Map<Long, Object> getAll() {
        return cartHistory.getContainer();
    }

    @Override
    public int insert(Long id, Object ignore) {
        return cartHistory.getContainer().put(id, null) != null ? 1 : 0;
    }
}
