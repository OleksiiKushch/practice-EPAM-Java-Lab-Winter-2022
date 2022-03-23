package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.model.entity.Order;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.repository.factory.RepositoryFactory;
import com.epam.task4.service.CartService;
import com.epam.task4.util.ConsoleColor;
import com.epam.task4.util.ProductDataScanner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.epam.task4.util.AppLiteral.MSG_ABILITY_CANCEL_OPERATION;
import static com.epam.task4.util.AppLiteral.MSG_WHEN_OPERATION_ABORT;
import static com.epam.task4.util.AppLiteral.SUM;

/**
 * @author Oleksii Kushch
 */
public class CartServiceImpl implements CartService {
    private static final String DEFAULT_SUM_IF_CART_IS_EMPTY = "0.0";

    private static final String MSG_ALERT_CART_IS_EMPTY = ConsoleColor.RED +
            "Cart is empty, please select and add the product you want to cart." + ConsoleColor.RESET;
    private static final String MSG_CHECKOUT_SUCCESS = ConsoleColor.GREEN +
            "Checkout was successful!" + ConsoleColor.RESET;
    private static final String MSG_PUT_PRODUCT_TO_CART_SUCCESS = ConsoleColor.GREEN +
            "Product with id: %d was successfully added to cart in amount: %d%n" + ConsoleColor.RESET;

    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public CartServiceImpl() {
        // default constructor
    }

    public CartServiceImpl(CartRepository cartRepository, OrderRepository orderRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void initMockRepository() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        cartRepository = repositoryFactory.getCartRepository();
        orderRepository = repositoryFactory.getOrderRepository();
        productRepository = repositoryFactory.getProductRepository();
    }

    @Override
    public List<Commodity> getContent() {
        return mapListProducts(cartRepository.getAll());
    }

    @Override
    public List<Map.Entry<Commodity, LocalDateTime>> getHistory() {
        return cartRepository.getHistory().entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(productRepository.getById(e.getKey()), e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void interactivePut() {
        System.out.println(MSG_ABILITY_CANCEL_OPERATION);
        Long id = ProductDataScanner.inputId(productRepository);
        if (id == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return;
        }
        Integer amount = ProductDataScanner.inputAmountForCart(id, productRepository, cartRepository);
        if (amount == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return;
        }
        put(id, amount);
        System.out.printf(MSG_PUT_PRODUCT_TO_CART_SUCCESS, id, amount);

    }

    /**
     * Imitation the logic of checkout an order (cart content). The following steps are carried out in the process:
     * <ul>
     *     <li> Create new order in order catalog ({@link com.epam.task4.mockdata.MockOrderCatalog})
     *     <li> output to console total sum of order (total sum the content of cart) and reports the success of the operation
     *     <li> clear the content of cart
     * </ul>
     * <p>
     * Noted: operation cannot be execute if cart is empty
     */
    @Override
    public void checkout() {
        if (cartRepository.getAll().isEmpty()) {
            System.out.println(MSG_ALERT_CART_IS_EMPTY);
        } else {
            cartRepository.getAll().forEach((key, value) -> { // process amount on stock (mock product catalog)
                Commodity commodity = productRepository.getById(key);
                commodity.setAmount(commodity.getAmount() - value);
            });
            orderRepository.insert(new Order(null, LocalDateTime.now(), mapListProducts(cartRepository.getAll())));
            System.out.println(MSG_CHECKOUT_SUCCESS);
            System.out.println(SUM + getSum());
            cartRepository.getAll().clear();
        }
    }

    /**
     * The sum of the multiply of the content (the price of the product and its quantity)
     * of all pairs of the container.
     * <p>
     * The product price is obtained by finding the corresponding product by its id (key in the cart
     * container from the product catalog ({@link com.epam.task4.mockdata.MockProductCatalog}).
     * @return total sum of the cart content
     */
    @Override
    public BigDecimal getSum() {
        return cartRepository.getAll().entrySet().stream()
                .map(entry -> productRepository.getById(entry.getKey()).getPrice()
                        .multiply(new BigDecimal(entry.getValue())))
                .reduce(new BigDecimal(DEFAULT_SUM_IF_CART_IS_EMPTY), BigDecimal::add);
    }

    /**
     * Add (put) products id to container and groups its summing their (products) amount
     * accordingly. Also add (put) product id to special container, building a kind
     * of stack-like structure, where the products (their id) are lined up in the order of the "last added".
     *
     * @param id the product id ({@link com.epam.task1.entity.Commodity})
     * @param amount amount (number of products)
     */
    private void put(Long id, Integer amount) {
        Map<Long, Integer> cartContainer = cartRepository.getAll();
        if (cartContainer.containsKey(id)) {
            cartRepository.insert(id, cartContainer.get(id) + amount);
        } else {
            cartRepository.insert(id, amount);
        }
        cartRepository.insertInHistory(id, LocalDateTime.now());
    }

    private List<Commodity> mapListProducts(Map<Long, Integer> container) {
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
