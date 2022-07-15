package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.composite_key.KhachHangID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoKhachHang extends JpaRepository<KhachHang, KhachHangID> {
    List<KhachHang> findByMaKhachHang(String maKhachHang);
}
