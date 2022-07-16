package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepoDanhGia extends JpaRepository<DanhGia,Long> {
}
