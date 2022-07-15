package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoDanhGia extends JpaRepository<DanhGia,Long> {
}
