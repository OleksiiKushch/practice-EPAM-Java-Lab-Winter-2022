package com.epam.task4.service.impl;

import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.factory.RepositoryFactory;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.OrderService;
import com.epam.task4.util.ConsoleColor;
import com.epam.task4.util.DateScanner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.epam.task4.util.AppLiteral.MSG_ABILITY_CANCEL_OPERATION;
import static com.epam.task4.util.AppLiteral.MSG_WHEN_OPERATION_ABORT;

/**
 * @author Oleksii Kushch
 */
public class OrderServiceImpl implements OrderService {
    private static final String MSG_ENTER_AFTER_YEAR = ConsoleColor.CYAN +
            "Please, enter FROM (after) date (year):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_AFTER_MONTH = ConsoleColor.CYAN +
            "Please, enter FROM (after) date (month number):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_AFTER_DAY = ConsoleColor.CYAN +
            "Please, enter FROM (after) date (day):" + ConsoleColor.RESET;

    private static final String MSG_ENTER_BEFORE_YEAR = ConsoleColor.CYAN +
            "Please, enter TO (before) date (year):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_BEFORE_MONTH = ConsoleColor.CYAN +
            "Please, enter TO (before) date (month number):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_BEFORE_DAY = ConsoleColor.CYAN +
            "Please, enter TO (before) date (day):" + ConsoleColor.RESET;

    private static final String MSG_ENTER_NEAREST_YEAR = ConsoleColor.CYAN +
            "Please, enter the nearest date (year):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_NEAREST_MONTH = ConsoleColor.CYAN +
            "Please, enter the nearest date (month number):" + ConsoleColor.RESET;
    private static final String MSG_ENTER_NEAREST_DAY = ConsoleColor.CYAN +
            "Please, enter the nearest date (day):" + ConsoleColor.RESET;

    private OrderRepository orderRepository;

    public OrderServiceImpl() {
        // default constructor
    }

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void initMockRepository() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
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
        System.out.println(MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime fromDate = Objects.requireNonNull(interactiveConsoleInputDate(
                MSG_ENTER_AFTER_YEAR, MSG_ENTER_AFTER_MONTH, MSG_ENTER_AFTER_DAY));

        LocalDateTime toDate = Objects.requireNonNull(interactiveConsoleInputDate(
                MSG_ENTER_BEFORE_YEAR, MSG_ENTER_BEFORE_MONTH, MSG_ENTER_BEFORE_DAY));

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
        System.out.println(MSG_ABILITY_CANCEL_OPERATION);

        LocalDateTime nearestDate = interactiveConsoleInputDate(
                MSG_ENTER_NEAREST_YEAR, MSG_ENTER_NEAREST_MONTH, MSG_ENTER_NEAREST_DAY);

        return orderRepository.getAll().stream()
                .min(Comparator.comparing(order ->
                        Math.abs(Duration.between(order.getDateTime(), nearestDate).toDays()))).orElse(null);
    }

    private LocalDateTime interactiveConsoleInputDate(
            String msgAppealForInputYear, String msgAppealForInputMonth, String msgAppealForInputDay) {
        System.out.println(msgAppealForInputYear);
        Integer year = DateScanner.inputYear();
        if (year == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println(msgAppealForInputMonth);
        Integer month = DateScanner.inputMonth();
        if (month == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        System.out.println(msgAppealForInputDay);
        Integer day = DateScanner.inputDay(year, month);
        if (day == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return null;
        }
        return LocalDateTime.of(year, month, day, 0, 0, 0);
    }
}
