package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoKhachHang;
import com.fake.Restaurant.repository.RepoMonAn;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<KhachHang> tim_khach_hang_ma(String maKhachHang) {

        List<KhachHang> khachHang=repoKhachHang.findByMaKhachHang(maKhachHang);
       if (khachHang == null){
           return null;
       }else{
           return khachHang;
       }
    }

    @Override
    public List<KhachHang> tim_khach_hang_ten_sdt(String ten, String sdt) {
        List<KhachHang> khachHang=repoKhachHang.findByTenAndSdtAndDanhGia(ten,sdt,null);

        if (khachHang.size() < 1){
            return null;
        }else{
            return khachHang;
        }
    }

    @Override
    public KhachHang tim_khach_hang_ten_sdt_ma(String ten, String sdt, String maDoAn) {
        Optional<KhachHang> khachHang=repoKhachHang.findByTenAndSdtAndMaDoAn(ten,sdt,maDoAn);
        log.info("{}",khachHang.get());
        if (khachHang.isEmpty()){
        return null;
        }else {
            return khachHang.get();
        }
    }

    @Override
    public void luu_ttkh(KhachHang kh) {
        repoKhachHang.save(kh);
    }

    @Override
    public List<KhachHang> cap_nhat_khach_hang_ten_sdt_tg(String ten, String sdt, LocalDateTime thoiGianDat) {
        List<KhachHang> khachHangs=repoKhachHang.findByTenAndSdtAndThoiGianDat(
                ten,sdt,thoiGianDat
        );
        khachHangs.forEach(kh ->{
            kh.setXacNhan(true);
        });
        repoKhachHang.saveAll(khachHangs);
        if (khachHangs.size() < 1) {
            return null;
        }else {
            return khachHangs;
        }
    }

    @Override
    public Map<KhachHang, List<KhachHang>> tim_ds_khach_hang_chua_tt(int trangThai) {
        if (trangThai == 0){
          List<KhachHang> khachHangs= repoKhachHang.findByXacNhan(false);

          return listToMap(khachHangs);
        }else if (trangThai == 1){
            List<KhachHang> khachHangs= repoKhachHang.findByXacNhan(
                    false, Sort.by(Sort.Order.desc("thoiGianDat"))
            );
            return listToMap(khachHangs);
        }else{
            List<KhachHang> khachHangs= repoKhachHang.findByXacNhan(
                    false, Sort.by(Sort.Order.asc("thoiGianDat"))
            );
            return listToMap(khachHangs);
        }

    }
    private Map<KhachHang,List<KhachHang>> listToMap(List<KhachHang> khachHangs){
        Map<KhachHang ,List<KhachHang>> map=new HashMap<>();
        for(KhachHang khachHang: khachHangs){
            if (map.isEmpty()){
                List<KhachHang> list=repoKhachHang.findByMaKhachHangAndXacNhan(
                        khachHang.getMaKhachHang(),
                        false
                );

                map.put(khachHang,list);
            }else{
                for (Iterator<KhachHang> it = map.keySet().iterator(); it.hasNext(); ) {
                    KhachHang kh = it.next();
                    if (kh.getMaKhachHang() != khachHang.getMaKhachHang()){
                        List<KhachHang> list=repoKhachHang.findByMaKhachHangAndXacNhan(
                                khachHang.getMaKhachHang(),
                                false
                        );
                        map.put(khachHang,list);
                        break;
                    }
                }
            };
        }


         return map;
    }

    private LocalDateTime hien_gio(){
        return LocalDateTime.now();
    }

}
