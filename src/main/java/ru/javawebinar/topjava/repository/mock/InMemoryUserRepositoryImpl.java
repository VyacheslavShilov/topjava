package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.*;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository  = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

   /* {
        final List<User> USERS = Arrays.asList(
                new User(0, "Анна", "anna@mail.ru", "123", Role.ROLE_USER),
                new User(1, "Валя", "val@mail.ru", "qwe", Role.ROLE_USER),
                new User(2, "Боря", "bor@mail.ru", "ryu", Role.ROLE_USER),
                new User(3, "Алиса", "alise@mail.ru", "fgh", Role.ROLE_USER));

        USERS.forEach(this::save);
    }

    public static void main(String[] args) {
        InMemoryUserRepositoryImpl impl = new InMemoryUserRepositoryImpl();
        impl.getAll();
        System.out.println();
    }*/

    @Override
    public boolean delete(int id) {
        repository.remove(id);
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            log.info("save new{}", user);
            return user;
        }
        // treat case: update, but absent in storage
        log.info("update {}", user);
        return repository.computeIfPresent(user.getId(), (id, oldMeal) -> user);

    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values()
                .stream()
                //сортировка по имени, а если они совпадают, то по id
                .sorted((u1, u2) -> !u1.getName().equals(u2.getName()) ? u1.getName().compareTo(u2.getName()) : u1.getId().compareTo(u2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
