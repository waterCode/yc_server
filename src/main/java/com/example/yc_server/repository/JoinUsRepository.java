package com.example.yc_server.repository;

import com.example.yc_server.domain.JoinUsForm;
import com.example.yc_server.domain.RegistrationForm;
import com.example.yc_server.domain.SysUser;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinUsRepository  extends JpaRepository<JoinUsForm,Long> {
    public JoinUsForm findByStudentNum(String num);

    @Override
    Optional<JoinUsForm> findById(Long aLong);
}
