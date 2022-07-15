package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDanhGia;
import com.fake.Restaurant.repository.RepoKhachHang;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.DanhGiaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DanhGiaServiceImp implements DanhGiaService {
    protected double TONG_SAO=5.0;
    @Autowired
    private RepoDanhGia repoDanhGia;
    @Autowired
    private AnotherService anotherService;
    @Override
    public List<DanhGia> ds_danh_gia_sp() {
        return repoDanhGia.findAll();
    }
    @Override
    public double kich_thuoc_sao(double sao) {
        return (sao/TONG_SAO)*100;
    }

    @Override
    public void deleteMemoryHome() {
        ds_danh_gia_sp().forEach(danhGia ->{
            try {
                anotherService.deleteImage(danhGia.getKhachHang().getMonAn().getTen(),"home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
