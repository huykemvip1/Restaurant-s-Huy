package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DatMangVe;
import com.fake.Restaurant.domain.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepoDatMangVe extends JpaRepository<DatMangVe,Long> {

    List<DatMangVe> findByKhachHang(KhachHang khachHang);
    @Modifying
    @Query(nativeQuery = true,value = "ALTER TABLE khach_hang_dat_mang_ve AUTO_INCREMENT = 1")
    void reset_relation();

    @Modifying
    @Query(nativeQuery = true,value = "ALTER TABLE dat_mang_ve AUTO_INCREMENT = 1")
    void reset_selfed();
}
