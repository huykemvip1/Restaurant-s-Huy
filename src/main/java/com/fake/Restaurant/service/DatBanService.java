package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.KhachHang;

import java.time.LocalDateTime;
import java.util.List;

public interface DatBanService {
    public List<DatBan> find_ban_trong(String time);
    List<DatBan> find_tg_trong();
    DatBan luu_dat_ban(DatBan datBan,String maKhachHang);
    DatBan tim_tt_ban(List<KhachHang> khachHangs);
}
