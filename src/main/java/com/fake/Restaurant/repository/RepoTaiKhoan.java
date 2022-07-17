package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepoTaiKhoan extends JpaRepository<TaiKhoan,String> {
}
