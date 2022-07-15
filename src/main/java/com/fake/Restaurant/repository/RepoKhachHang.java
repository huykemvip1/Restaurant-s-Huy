package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.composite_key.KhachHangID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoKhachHang extends JpaRepository<KhachHang, KhachHangID> {
    Optional<KhachHang> findByMaKhachHang(String maKhachHang);
}
