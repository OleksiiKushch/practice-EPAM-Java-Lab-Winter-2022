package com.epam.task6.create_product.mirror_wrapper_entity;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task6.create_product.Initializable;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class InitCommodity extends Commodity implements Initializable<Commodity> {
    private static final String FRONT_TITLE = "FrontTitle";

    private Commodity commodity;

    public InitCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    @Override
    public Commodity initViaConsole(Commodity commodity, ProductDataConsoleScanner productDataConsoleScanner) {
        Long id = productDataConsoleScanner.inputId();
        if (id == null) {
            return null;
        }
        commodity.setId(id);

        String frontTitle = productDataConsoleScanner.inputFrontTitle();
        if (frontTitle == null) {
            return null;
        }
        commodity.setFrontTitle(frontTitle);

        BigDecimal price = productDataConsoleScanner.inputPrice();
        if (price == null) {
            return null;
        }
        commodity.setPrice(price);

        Integer amount = productDataConsoleScanner.inputAmount();
        if (amount == null) {
            return null;
        }
        commodity.setAmount(amount);

        return commodity;
    }

    @Override
    public Commodity autoInit(Commodity commodity, ProductRepository productRepository, SecureRandom secureRandom) {


        long id = secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productRepository.getById(id) != null) {
            id = secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        commodity.setId(id);
        commodity.setFrontTitle(FRONT_TITLE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        commodity.setPrice(new BigDecimal(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM)));
        commodity.setAmount(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));

        return commodity;
    }
}
