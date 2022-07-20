package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DatMangVe;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDatMangVe;
import com.fake.Restaurant.service.DatMangVeService;
import com.fake.Restaurant.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class DatMangVeServiceImp implements DatMangVeService {
    @Autowired
    private RepoDatMangVe repoDatMangVe;
    @Autowired
    private KhachHangService khachHangService;
    @Override
    @Transactional
    public boolean luu_thong_tin_dat_ve(DatMangVe datMangVe,String maKhachHang) {
        int leftLimit=97;
        int rightLimit=122;
        Random random=new Random();
        String chuoi=random.ints(leftLimit,rightLimit+1)
                .limit(6)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();

        List<KhachHang> khachHang=khachHangService.tim_khach_hang_ma(maKhachHang);
        if (khachHang == null){
            return false;
        }else{
            repoDatMangVe.reset_relation();
            repoDatMangVe.reset_selfed();
            datMangVe.setMaDatDo(chuoi);
            datMangVe.setKhachHang(khachHang);
            repoDatMangVe.save(datMangVe);
        }
        return true;
    }
}
