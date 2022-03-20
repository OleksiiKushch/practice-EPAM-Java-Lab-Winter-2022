package com.epam.task4.service.impl;

import com.epam.task4.dal.OrderRepository;
import com.epam.task4.dal.RepositoryFactory;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.DateScanner;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final String MSG_WHEN_OPERATION_ABORT = "Abort operation successful!";

    private OrderRepository orderRepository;

    public OrderServiceImpl() {

    }

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void initMockRepository() {
        RepositoryFactory repositoryFactory = null;
        try {
            repositoryFactory = RepositoryFactory.getInstance();
        } catch (NoSuchMethodException | IllegalAccessException |
                InvocationTargetException | InstantiationException exception) {
            exception.printStackTrace();
        }
        Objects.requireNonNull(repositoryFactory);
        orderRepository = repositoryFactory.getOrderRepository();
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
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");

        System.out.println("Please, enter FROM (after) date (year):");
        Integer fromYear = DateScanner.inputYear();
        if (fromYear == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter FROM (after) date (month number):");
        Integer fromMonth = DateScanner.inputMonth(fromYear);
        if (fromMonth == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter FROM (after) date (day):");
        Integer fromDay = DateScanner.inputDay(fromYear, fromMonth);
        if (fromDay == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        LocalDateTime fromDate = LocalDateTime.of(fromYear, fromMonth, fromDay, 0, 0, 0);

        System.out.println("Please, enter TO (before) date (year):");
        Integer toYear = DateScanner.inputYear();
        if (toYear == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter TO (before) date (month number):");
        Integer toMonth = DateScanner.inputMonth(toYear);
        if (toMonth == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter TO (before) date (day):");
        Integer toDay = DateScanner.inputDay(toYear, toMonth);
        if (toDay == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        LocalDateTime toDate = LocalDateTime.of(toYear, toMonth, toDay, 0, 0, 0);

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
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");

        System.out.println("Please, enter the nearest date (year):");
        Integer year = DateScanner.inputYear();
        if (year == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter the nearest date (month number):");
        Integer month = DateScanner.inputMonth(year);
        if (month == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println("Please, enter the nearest date (day):");
        Integer day = DateScanner.inputDay(year, month);
        if (day == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }

        LocalDateTime nearestDate = LocalDateTime.of(year, month, day, 0, 0, 0);

        return orderRepository.getAll().stream()
                .min(Comparator.comparing(order ->
                        Math.abs(Duration.between(order.getDateTime(), nearestDate).toDays()))).orElse(null);
    }
}
