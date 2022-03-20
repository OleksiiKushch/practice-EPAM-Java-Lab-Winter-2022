package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.dal.CartHistoryRepository;
import com.epam.task4.dal.CartRepository;
import com.epam.task4.dal.OrderRepository;
import com.epam.task4.dal.ProductRepository;
import com.epam.task4.dal.RepositoryFactory;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.CartService;
import com.epam.task4.util.ProductDataScanner;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartServiceImpl implements CartService {
    private static final String MSG_WHEN_OPERATION_ABORT = "Abort operation successful!";

    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CartHistoryRepository cartHistoryRepository;

    public CartServiceImpl() {

    }

    public CartServiceImpl(CartRepository cartRepository, OrderRepository orderRepository,
                           ProductRepository productRepository, CartHistoryRepository cartHistoryRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartHistoryRepository = cartHistoryRepository;
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
        cartRepository = repositoryFactory.getCartRepository();
        orderRepository = repositoryFactory.getOrderRepository();
        productRepository = repositoryFactory.getProductRepository();
        cartHistoryRepository = repositoryFactory.getCartHistoryRepository();
    }

    @Override
    public Commodity getProductById(Long id) {
        Commodity result = productRepository.getAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst().orElse(null);
        if (result != null) {
            result.setAmount(cartRepository.getAll().get(id));
        }
        return result;
    }

    @Override
    public List<Commodity> getAllProducts() {
        return mapListProducts(cartRepository.getAll());
    }

    @Override
    public void interactivePut() {
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");
        Long id = ProductDataScanner.inputId(productRepository);
        if (id == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return;
        }
        Integer amount = ProductDataScanner.inputAmount(id, productRepository, cartRepository);
        if (amount == null) {
            System.out.println(MSG_WHEN_OPERATION_ABORT);
            return;
        }
        put(id, amount);
        System.out.println("Product with id: " + id + " was successfully added to cart in amount: " + amount);

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
        cartHistoryRepository.insert(id, null);
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
            System.out.println("Cart is empty, please select and add the product you want to cart");
        } else {
            cartRepository.getAll().forEach((key, value) -> { // process amount on stock (mock product catalog)
                Commodity commodity = productRepository.getById(key);
                commodity.setAmount(commodity.getAmount() - value);
                productRepository.update(commodity);
            });
            orderRepository.insert(new Order(null, LocalDateTime.now(), mapListProducts(cartRepository.getAll())));
            System.out.println("Sum: " + getSum());
            System.out.println("Checkout was successful!");
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
                .reduce(new BigDecimal("0.0"), BigDecimal::add);
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
