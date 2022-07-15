package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.exception.ValueDoesNotExist;
import com.fake.Restaurant.repository.RepoKhachHang;
import com.fake.Restaurant.repository.RepoMonAn;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KhachHangServiceImp implements KhachHangService {
    @Autowired
    private RepoMonAn repoMonAn;
    @Autowired
    private RepoKhachHang repoKhachHang;
    @Override
    public KhachHang an_sdt(KhachHang khachHang) {

            khachHang.setSdt(
                    khachHang.getSdt().substring(0,6)+"xxxx"
            );

        return khachHang;
    }

    @Override
    public KhachHang luu_ttkh_tu_DataCart(List<DataCart> dataCarts,KhachHang khachHang) {

        if (dataCarts != null){

            KhachHang kh=KhachHang.builder()
                    .ten(khachHang.getTen())
                    .soLuong(dataCarts.get(0).getSoLuong())
                    .tenThe(khachHang.getTenThe())
                    .email(khachHang.getEmail())
                    .sdt(khachHang.getSdt())
                    .thoiGianDat(hien_gio())
                    .xacNhan(false)
                    .maDoAn(dataCarts.get(0).getMaMonAn())
                    .build();
            KhachHang khachHang1=repoKhachHang.save(kh);
            log.info("{}",khachHang1);
            if (dataCarts.size() > 1){
                for(DataCart dataCart: dataCarts.subList(1,dataCarts.size())){
                    KhachHang newKH=KhachHang.builder()
                            .maKhachHang(khachHang1.getMaKhachHang())
                            .maDoAn(dataCart.getMaMonAn())
                            .tenThe(khachHang1.getTenThe())
                            .email(khachHang1.getTenThe())
                            .soLuong(dataCart.getSoLuong())
                            .sdt(khachHang1.getSdt())
                            .thoiGianDat(khachHang1.getThoiGianDat())
                            .xacNhan(khachHang1.getXacNhan())
                            .ten(khachHang1.getTen())
                            .build();
                    KhachHang hh= repoKhachHang.save(newKH);
                    log.info("{}",hh);
                }
            }
            return khachHang1;
        }else {
            return  null;
        }
    }

    @Override
    public KhachHang tim_khach_hang_ma(String maKhachHang) {
        Optional<KhachHang> khachHang=repoKhachHang.findByMaKhachHang(maKhachHang);
       if (khachHang.isEmpty()){
           return null;
       }else{
           return khachHang.get();
       }
    }


    private LocalDateTime hien_gio(){
        return LocalDateTime.now();
    }

}
