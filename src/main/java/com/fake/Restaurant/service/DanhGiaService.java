package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;

import java.util.List;

public interface DanhGiaService {
    List<DanhGia> ds_danh_gia_sp();
    double kich_thuoc_sao(double sao);
    void deleteMemoryHome();
}
