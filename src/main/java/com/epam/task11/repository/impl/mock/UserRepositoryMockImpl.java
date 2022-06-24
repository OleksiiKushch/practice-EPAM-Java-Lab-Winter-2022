package com.epam.task11.repository.impl.mock;

import com.epam.task11.entity.User;
import com.epam.task11.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMockImpl implements UserRepository {
    @Override
    public void insert(User user) {
        UserCatalog.container.add(user);
    }

    @Override
    public boolean isContainUser(String email) {
        return UserCatalog.container.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private static class UserCatalog {
        public static final List<User> container = new ArrayList<>();

        public UserCatalog() {
            fillContainerMockData();
        }

        private void fillContainerMockData() {
            container.add(new User.Builder().withEmail("unicronix2001@gmail.com").build());
            container.add(new User.Builder().withEmail("hello@i.ua").build());
        }
    }
}
