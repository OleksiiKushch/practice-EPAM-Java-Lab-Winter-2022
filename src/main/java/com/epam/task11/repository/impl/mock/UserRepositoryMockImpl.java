package com.epam.task11.repository.impl.mock;

import com.epam.task11.entity.user.User;
import com.epam.task11.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class UserRepositoryMockImpl implements UserRepository {
    private final UserCatalog userCatalog;

    public UserRepositoryMockImpl() {
        userCatalog = new UserCatalog();
    }

    @Override
    public void insert(User user) {
        userCatalog.getContainer().add(user);
    }

    @Override
    public boolean isContainUser(String email) {
        return userCatalog.getContainer().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private static class UserCatalog {
        private final List<User> container;

        public UserCatalog() {
            container = new ArrayList<>();
            fillContainerMockData();
        }

        public List<User> getContainer() {
            return container;
        }

        private void fillContainerMockData() {
            container.add(new User.Builder().withEmail("unicronix2001@gmail.com").build());
            container.add(new User.Builder().withEmail("hello@i.ua").build());
        }
    }
}
