package com.salah.ask.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testNotNull(){
        assertThat(userRepository).isNotNull();
    }
}
