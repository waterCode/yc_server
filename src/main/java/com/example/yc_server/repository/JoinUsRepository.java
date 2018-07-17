package com.example.yc_server.repository;

import com.example.yc_server.domain.JoinUsForm;
import com.example.yc_server.domain.SysUser;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinUsRepository  extends JpaRepository<JoinUsForm,Integer> {
    public JoinUsForm findByStudentNum(String num);
}
