package com.epam.task4.service.impl;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.model.entity.Order;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.DateScanner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    /**
     * The interactive part of the operation execution consists of six parts:
     * <ul>
     *     <li> Entering the year (start-point) from the console
     *     <li> Entering the month (month number) (start-point) from the console
     *     <li> Entering the day (start-point) from the console
     *     <li> Entering the year from (end-point) the console
     *     <li> Entering the month (month number) (end-point) from the console
     *     <li> Entering the day (end-point) from the console
     * </ul>
     * On any of these six parts, it is possible to stop execution of all operation if the corresponding methods
     * for reading data from the console ({@link DateScanner#inputYear}, {@link DateScanner#inputMonth},
     * {@link DateScanner#inputDay}) return {@code null}.
     */
    @Override
    public List<Order> getOrdersFromToByDate() {
        System.out.println(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime fromDate = Objects.requireNonNull(interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_AFTER_YEAR,
                ShopLiterals.MSG_ENTER_AFTER_MONTH, ShopLiterals.MSG_ENTER_AFTER_DAY));

        LocalDateTime toDate = Objects.requireNonNull(interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_BEFORE_YEAR,
                ShopLiterals.MSG_ENTER_BEFORE_MONTH, ShopLiterals.MSG_ENTER_BEFORE_DAY));

        return orderRepository.getAll().stream()
                .filter(order -> order.getDateTime().isAfter(fromDate) && order.getDateTime().isBefore(toDate))
                .collect(Collectors.toList());
    }

    /**
     * Noted: The interactive part of the operation execution consists of three parts:
     * <ul>
     *     <li> Entering the year from the console
     *     <li> Entering the month (month number) from the console
     *     <li> Entering the day from the console
     * </ul>
     * On any of these three parts, it is possible to stop execution of all operation (this method execute())
     * if the corresponding methods for reading data from the console ({@link DateScanner#inputYear}, {@link DateScanner#inputMonth},
     * {@link DateScanner#inputDay}) return {@code null}.
     */
    @Override
    public Order getOrderByNearestDate() {
        System.out.println(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime nearestDate = interactiveConsoleInputDate(ShopLiterals.MSG_ENTER_NEAREST_YEAR,
                ShopLiterals.MSG_ENTER_NEAREST_MONTH, ShopLiterals.MSG_ENTER_NEAREST_DAY);

        return orderRepository.getAll().stream()
                .min(Comparator.comparing(order ->
                        Math.abs(Duration.between(order.getDateTime(), nearestDate).toDays()))).orElse(null);
    }

    private LocalDateTime interactiveConsoleInputDate(
            String msgAppealForInputYear, String msgAppealForInputMonth, String msgAppealForInputDay) {
        System.out.println(msgAppealForInputYear);
        Integer year = DateScanner.inputYear();
        if (year == null) {
            System.out.println(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println(msgAppealForInputMonth);
        Integer month = DateScanner.inputMonth();
        if (month == null) {
            System.out.println(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println(msgAppealForInputDay);
        Integer day = DateScanner.inputDay(year, month);
        if (day == null) {
            System.out.println(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        return LocalDateTime.of(year, month, day, 0, 0, 0);
    }
}
