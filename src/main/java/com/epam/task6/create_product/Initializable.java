package com.epam.task6.create_product;

import com.epam.task4.repository.ProductRepository;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.security.SecureRandom;

public interface Initializable<T> {
    T initViaConsole(T t, ProductDataConsoleScanner productDataConsoleScanner);
    T autoInit(T t, ProductRepository productRepository, SecureRandom secureRandom);
}
