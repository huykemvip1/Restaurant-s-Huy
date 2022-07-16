package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.composite_key.KhachHangID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface RepoKhachHang extends JpaRepository<KhachHang, KhachHangID> {
    List<KhachHang> findByMaKhachHang(String maKhachHang);

    List<KhachHang> findByTenAndSdtAndDanhGia(String ten, String sdt, DanhGia danhGia);
}
