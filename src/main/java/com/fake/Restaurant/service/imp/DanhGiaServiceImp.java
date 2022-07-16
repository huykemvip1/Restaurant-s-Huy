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
    @Override
    public List<DanhGia> ds_danh_gia_sp() throws IOException {

        List<DanhGia> danhGias=repoDanhGia.findAll();
        int index=0;
        for (DanhGia danhGia:danhGias){
            danhGias.get(index).setSoSao(kich_thuoc_sao(
                    danhGia.getSoSao()
            ));

            danhGias.get(index).setKhachHang(
                    khachHangService.an_sdt(danhGia.getKhachHang())
            );

            anotherService.saveImage(danhGia.getKhachHang().getMonAn().getAnhMonAn(),
                    danhGia.getKhachHang().getTen(),"home");
            index++;
        }
        return danhGias;
    }
    @Override
    public double kich_thuoc_sao(double sao) {
        return (sao/TONG_SAO)*100.0;
    }

    @Override
    public void deleteMemoryHome() throws IOException {
        ds_danh_gia_sp().forEach(danhGia ->{
            try {
                anotherService.deleteImage(danhGia.getKhachHang().getMonAn().getTen(),"home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
