package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.repository.RepoKhachHang;
import com.fake.Restaurant.repository.RepoMonAn;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
        List<KhachHang> khachHangList=new ArrayList<>();
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
            Optional<MonAn> monAn= repoMonAn.findById(dataCarts.get(0).getMaMonAn());
            monAn.get().setSoLuongSd(monAn.get().getSoLuongSd()+dataCarts.get(0).getSoLuong());
            khachHangList.add(kh);
            repoMonAn.save(monAn.get());

            if (dataCarts.size() > 1){
                for(DataCart dataCart: dataCarts.subList(1,dataCarts.size())){
                    KhachHang newKH=KhachHang.builder()
                            .maDoAn(dataCart.getMaMonAn())
                            .tenThe(khachHang.getTenThe())
                            .email(khachHang.getEmail())
                            .soLuong(dataCart.getSoLuong())
                            .sdt(khachHang.getSdt())
                            .thoiGianDat(hien_gio())
                            .xacNhan(false)
                            .ten(khachHang.getTen())
                            .build();
                    khachHangList.add(newKH);
                    Optional<MonAn> mA= repoMonAn.findById(dataCart.getMaMonAn());
                    mA.get().setSoLuongSd(mA.get().getSoLuongSd()+dataCart.getSoLuong());
                    repoMonAn.save(mA.get());
                }
            }
            List<KhachHang> list= repoKhachHang.saveAll(khachHangList);


            return list.get(0);
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
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    @Override
    public Map<KhachHang, List<KhachHang>> tim_ds_khach_hang_chua_tt(int trangThai) {
        if (trangThai == 0){
          List<KhachHang> khachHangs= repoKhachHang.findByXacNhan(false);
          /*
          List<KhachHang> khachHangSDistinct=new ArrayList<>();

         for (KhachHang khachHang:khachHangs){
             int dem=0;
             if (khachHangSDistinct.size() < 1){
                 khachHangSDistinct.add(khachHang);
             }else{
                 for(KhachHang kh: khachHangSDistinct){

                     if (kh.getMaKhachHang().equals(khachHang.getMaKhachHang())){
                         dem++;
                     }
                 }
                 if(dem < 1){
                     khachHangSDistinct.add(khachHang);
                 }
             }
         }
* */
          return listToMap(
                  khachHangs.
                          stream()
                          .filter(distinctByKey(a ->a.getMaKhachHang()))
                          .distinct()
                          .collect(Collectors.toList())
          );
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

                List<KhachHang> list=repoKhachHang.findByMaKhachHangAndXacNhan(
                        khachHang.getMaKhachHang(),
                        false
                );

                map.put(khachHang,list);
                log.info("{}",list.size());
            }
        return map;
        }


    private LocalDateTime hien_gio(){
        return LocalDateTime.now();
    }

}
