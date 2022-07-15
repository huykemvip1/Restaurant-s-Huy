package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DatMangVe;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDatMangVe;
import com.fake.Restaurant.service.DatMangVeService;
import com.fake.Restaurant.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DatMangVeServiceImp implements DatMangVeService {
    @Autowired
    private RepoDatMangVe repoDatMangVe;
    @Autowired
    private KhachHangService khachHangService;
    @Override
    public boolean luu_thong_tin_dat_ve(DatMangVe datMangVe,String maKhachHang) {
        KhachHang khachHang=khachHangService.tim_khach_hang_ma(maKhachHang);
        if (khachHang == null){
            return false;
        }
        datMangVe.setKhachHang(khachHang);
        repoDatMangVe.save(datMangVe);
        return true;
    }
}
