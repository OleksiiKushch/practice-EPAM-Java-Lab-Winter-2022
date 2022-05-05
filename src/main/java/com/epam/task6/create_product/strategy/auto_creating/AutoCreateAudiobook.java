package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task6.util.ProductDataRandomGenerator;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateAudiobook extends AutoCreateBook {
    public AutoCreateAudiobook(ProductDataRandomGenerator productDataRandomGenerator) {
        super(productDataRandomGenerator);
    }

    @Override
    public Commodity create() {
        Audiobook audiobook = new Audiobook((Book) super.create());

        audiobook.setFileSizeMB(productDataRandomGenerator.getRandomAudiobookFileSizeMB());
        audiobook.setListeningTimeMinutes(productDataRandomGenerator.getRandomAudiobookListeningTimeMinutes());
        audiobook.setNarrator(productDataRandomGenerator.getRandomAudiobookNarrator());

        return audiobook;
    }
}
