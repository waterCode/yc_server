package com.example.yc_server.repository;

import com.example.yc_server.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<SysUser,Integer> {

    public List<SysUser> findByUsername(String name);
}
