package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.exception.ValueDoesNotExist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface KhachHangService {
    KhachHang an_sdt(KhachHang khachHang);
    KhachHang luu_ttkh_tu_DataCart(List<DataCart> dataCarts,KhachHang khachHang);
    List<KhachHang> tim_khach_hang_ma(String maKhachHang);

    List<KhachHang> tim_khach_hang_ten_sdt(String ten, String sdt);

    KhachHang tim_khach_hang_ten_sdt_ma(String ten, String sdt, String maDoAn);

    void luu_ttkh(KhachHang kh);

    List<KhachHang> cap_nhat_khach_hang_ten_sdt_tg(String ten, String sdt, LocalDateTime thoiGianDat);
    Map<KhachHang, List<KhachHang>> tim_ds_khach_hang_chua_tt(int trangThai);
}
