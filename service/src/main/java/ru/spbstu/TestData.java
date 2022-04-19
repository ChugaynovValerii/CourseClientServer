package ru.spbstu;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.spbstu.entity.User;
import ru.spbstu.repository.UserRepository;

import java.util.Collections;

@Component
@Log4j2
public class TestData implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TestData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        log.info("before deleted records and actual size=" + userRepository.findAll().size());
        userRepository.deleteAll();
        log.info("deleted records and actual size=" + userRepository.findAll().size());
        userRepository.save(new User("admin", passwordEncoder.encode("admin"),
                Collections.singletonList("ROLE_ADMIN")));
        log.info("upserted records and actual size=" + userRepository.findAll().size());
    }
}
