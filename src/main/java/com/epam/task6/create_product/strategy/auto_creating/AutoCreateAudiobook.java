package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;

import java.security.SecureRandom;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateAudiobook extends AutoCreateBook {
    public static final String NARRATOR = "Narrator";

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        Audiobook audiobook = new Audiobook((Book) super.create());

        audiobook.setFileSizeMB(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setListeningTimeMinutes(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setNarrator(NARRATOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));

        return audiobook;
    }
}
