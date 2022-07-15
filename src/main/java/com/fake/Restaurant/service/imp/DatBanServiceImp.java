package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.composite_key.DatBanID;
import com.fake.Restaurant.repository.RepoDatBan;
import com.fake.Restaurant.service.DatBanService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class DatBanServiceImp implements DatBanService {
    @Autowired
    private RepoDatBan repoDatBan;
    @Autowired
    private KhachHangService khachHangService;
    @Override
    public List<DatBan> find_ban_trong(String time) {
        return repoDatBan.findAllByGioAn(time);
    }

    @Override
    public List<DatBan> find_tg_trong() {
        List<DatBan> list=new ArrayList<>();
                for(DatBan datBan: repoDatBan.findByKhachHang(null)){
                    if (list.size() < 1){
                        list.add(datBan);

                    }else {
                       int index=0;
                        for(DatBan db:list){

                            if (!datBan.getThoiGianSuDung().
                                    equals(db.getThoiGianSuDung())
                            ){
                                index++;
                            }
                        }
                       if (index == list.size()){
                           log.info("{}---{}",index,list.size());
                           list.add(datBan);
                       }
                    }
                }

        return list;
    }

    @Override
    public boolean luu_dat_ban(DatBan datBan,String maKhachHang) {
        DatBan db=repoDatBan.findByThoiGianSuDungAndSoLuongNguoi(
                datBan.getThoiGianSuDung(),
                datBan.getSoLuongNguoi()
        );
        KhachHang khachHang=khachHangService.tim_khach_hang_ma(maKhachHang);
        if (khachHang == null){
            return false;
        }else {
            db.setKhachHang(khachHang);
            repoDatBan.save(db);
            return true;
        }
    }
}
