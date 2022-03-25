package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
public interface CartService extends Service {
    List<Commodity> getContent();
    List<Map.Entry<Commodity, LocalDateTime>> getHistory();
    void interactivePut();
    void checkout();
    BigDecimal getSum();
}
