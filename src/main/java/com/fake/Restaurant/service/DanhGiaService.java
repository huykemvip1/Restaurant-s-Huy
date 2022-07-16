package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;

import java.io.IOException;
import java.util.List;

public interface DanhGiaService {
    List<DanhGia> ds_danh_gia_sp() throws IOException;
    double kich_thuoc_sao(double sao);
    void deleteMemoryHome() throws IOException;
}
