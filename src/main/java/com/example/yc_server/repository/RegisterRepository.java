package com.example.yc_server.repository;

import com.example.yc_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<User,Integer> {

    public List<User> findByName(String name);
}
