package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.exception.ValueDoesNotExist;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    KhachHang an_sdt(KhachHang khachHang);
    KhachHang luu_ttkh_tu_DataCart(List<DataCart> dataCarts,KhachHang khachHang);
    KhachHang tim_khach_hang_ma(String maKhachHang);
}
