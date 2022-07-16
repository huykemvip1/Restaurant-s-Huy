package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.exception.ValueDoesNotExist;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    KhachHang an_sdt(KhachHang khachHang);
    KhachHang luu_ttkh_tu_DataCart(List<DataCart> dataCarts,KhachHang khachHang);
    List<KhachHang> tim_khach_hang_ma(String maKhachHang);

    List<KhachHang> tim_khach_hang_ten_sdt(String ten, String sdt);

    KhachHang tim_khach_hang_ten_sdt_ma(String ten, String sdt, String maDoAn);

    void luu_ttkh(KhachHang kh);
}
