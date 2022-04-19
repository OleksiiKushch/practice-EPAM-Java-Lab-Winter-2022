package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;

import java.security.SecureRandom;

public class AutoCreateAudiobook extends AutoCreateBook {
    private static final String NARRATOR = "Narrator";

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        Audiobook audiobook = new Audiobook((Book) super.create());

        audiobook.setSizeMB(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setListeningLength(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setNarrator(NARRATOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));

        return audiobook;
    }
}
