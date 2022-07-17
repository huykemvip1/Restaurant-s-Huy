package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepoNhanVien extends JpaRepository<NhanVien,String> {
}
