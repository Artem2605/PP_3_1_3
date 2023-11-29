package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //todo: выяснить как решается проблема n+1 с помощью JOIN_FETCH (когда указываем в связи - @ManyToMany(fetch = FetchType.LAZY)). Реализовать
    User findByUsername(String username);
}