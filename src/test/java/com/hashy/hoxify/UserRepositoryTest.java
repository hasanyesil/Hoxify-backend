package com.hashy.hoxify;

import com.hashy.hoxify.user.User;
import com.hashy.hoxify.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsername_whenUserExists_returnsUser(){
        User user = new User();

        user.setUsername("test-user");
        user.setDisplayName("test-display");
        user.setPassword("P4ssword");

        testEntityManager.persist(user);
        User inDb = userRepository.findByUsername("test-user");
        assertThat(inDb).isNotNull();
    }

    @Test
    public void findByUsername_whenUserDoesNotExists_returnNull(){
        User inDb = userRepository.findByUsername("test-user");
        assertThat(inDb).isNull();
    }
}
