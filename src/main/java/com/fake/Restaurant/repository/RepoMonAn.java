package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.LoaiMonAn;
import com.fake.Restaurant.domain.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RepoMonAn extends JpaRepository<MonAn,String> {
    @Query(value = "select m from MonAn m where m.tongSoLuong-soLuongSd = 0")
    List<MonAn> checkExistGoods();

    @Query(value = "select m from MonAn m where m.loaiMonAn = :loai_mon_an")
    List<MonAn> findAllByLoaiMonAn(@Param("loai_mon_an") LoaiMonAn loaiMonAn);
}
