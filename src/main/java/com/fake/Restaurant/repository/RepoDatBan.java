package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.composite_key.DatBanID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RepoDatBan extends JpaRepository<DatBan, DatBanID> {
    @Query(value = "select d from DatBan d where " +
            "d.thoiGianSuDung = :time " +
            "and d.khachHang = null")
    List<DatBan> findAllByGioAn(@Param("time") String time);
    List<DatBan> findByKhachHang(KhachHang khachHang);
    DatBan findByThoiGianSuDungAndSoLuongNguoi(String thoiGianSuDung,int soLuongNguoi);
}
