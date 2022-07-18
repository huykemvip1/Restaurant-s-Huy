package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDanhGia;
import com.fake.Restaurant.repository.RepoKhachHang;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.DanhGiaService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DanhGiaServiceImp implements DanhGiaService {
    protected double TONG_SAO=5.0;
    @Autowired
    private RepoDanhGia repoDanhGia;
    @Autowired
    private AnotherService anotherService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private RepoKhachHang repoKhachHang;

    @Override
    public List<KhachHang> ds_danh_gia_sp() throws IOException {

        List<KhachHang> khachHangs=repoKhachHang.findByDanhGiaIsNotNull();
        int index=0;
        for (KhachHang khachHang: khachHangs){
            khachHangs.get(index).getDanhGia().setSoSao(kich_thuoc_sao(
                    khachHang.getDanhGia().getSoSao()
            ));

            KhachHang khachHang1=khachHangService.an_sdt(khachHang);
            khachHangs.set(index,khachHang1);


            anotherService.saveImage(khachHang.getMonAn().getAnhMonAn(),
                    khachHang.getMonAn().getTen(),"home");
            index++;
        }
        return khachHangs;
    }
    @Override
    public double kich_thuoc_sao(double sao) {
        return (sao/TONG_SAO)*100.0;
    }

    @Override
    public KhachHang luu_danh_gia(KhachHang khachHang) {
        KhachHang kh=khachHangService.tim_khach_hang_ten_sdt_ma(khachHang.getTen()
                ,khachHang.getSdt(),khachHang.getMaDoAn());
        log.info("{}",kh);
        if (khachHang == null){
            return null;
        }else{
            DanhGia danhGia=DanhGia.builder()
                    .binhLuan(khachHang.getDanhGia().getBinhLuan())
                    .soSao(khachHang.getDanhGia().getSoSao())
                    .build();
            kh.setDanhGia(danhGia);
            repoKhachHang.save(kh);
            return kh;
        }
    }

    @Override
    public void deleteMemoryHome() throws IOException {
        ds_danh_gia_sp().forEach(danhGia ->{
            try {
                anotherService.deleteImage(danhGia.getMonAn().getTen(),"home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
