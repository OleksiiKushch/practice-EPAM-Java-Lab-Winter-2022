package com.epam.task6.strategy.create_product;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.util.ProductDataScanner;

import java.math.BigDecimal;

public class ManualConsoleProductCreatingStrategy implements ProductCreatingStrategy {
    @Override
    public Commodity createProduct(ProductRepository productRepository) {
        Commodity result = null;
        Class type = ProductDataScanner.inputType();
        if (type == Book.class) {
            result = new Book();
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setBookParameters(result);
            if (result == null) { return null; }
        } else if (type == Audiobook.class) {
            result = new Audiobook();
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setAudiobookParameters(result);
            if (result == null) { return null; }
        } else if (type == EReader.class) {
            result = new EReader();
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setEReaderParameters(result);
            if (result == null) { return null; }
        }
        return result;
    }

    private Commodity setBaseParameters(Commodity product, ProductRepository productRepository) {
        Long id = ProductDataScanner.inputIdForCatalog(productRepository);
        if (id == null) { return null; }
        product.setId(id);

        String frontTitle = ProductDataScanner.inputFrontTitle();
        if (frontTitle == null) { return null; }
        product.setFrontTitle(frontTitle);

        BigDecimal price = ProductDataScanner.inputPrice();
        if (price == null) { return null; }
        product.setPrice(price);

        Integer amount = ProductDataScanner.inputAmountForStock();
        if (amount == null) { return null; }
        product.setAmount(amount);

        return product;
    }

    private Commodity setBookParameters(Commodity product) {
        Book book = (Book) product;

        String title = ProductDataScanner.inputTitle();
        if (title == null) { return null; }
        book.setTitle(title);

        String author = ProductDataScanner.inputAuthor();
        if (author == null) { return null; }
        book.setAuthor(author);

        String language = ProductDataScanner.inputLanguage();
        if (language == null) { return null; }
        book.setLanguage(language);

        Integer numberOfPages = ProductDataScanner.inputNumberOfPages();
        if (numberOfPages == null) { return null; }
        book.setNumberOfPages(numberOfPages);

        return book;
    }

    private Commodity setAudiobookParameters(Commodity product) {
        Audiobook audiobook = (Audiobook) product;

        Integer sizeMB = ProductDataScanner.inputSizeMB();
        if (sizeMB == null) { return null; }
        audiobook.setSizeMB(sizeMB);

        Integer listeningLength = ProductDataScanner.inputListeningLength();
        if (listeningLength == null) { return null; }
        audiobook.setListeningLength(listeningLength);

        String narrator = ProductDataScanner.inputNarrator();
        if (narrator == null) { return null; }
        audiobook.setNarrator(narrator);

        return audiobook;
    }

    private Commodity setEReaderParameters(Commodity product) {
        EReader eReader = (EReader) product;

        String model = ProductDataScanner.inputModel();
        if (model == null) { return null; }
        eReader.setModel(model);

        Float displaySize = ProductDataScanner.inputDisplaySize();
        if (displaySize == null) { return null; }
        eReader.setDisplaySize(displaySize);

        Integer storageGB = ProductDataScanner.inputStorageGB();
        if (storageGB == null) { return null; }
        eReader.setStorageGB(storageGB);

        Integer resolutionPPI = ProductDataScanner.inputResolutionPPI();
        if (resolutionPPI == null) { return null; }
        eReader.setResolutionPPI(resolutionPPI);

        return eReader;
    }
}
