package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.TaiKhoan;
import com.fake.Restaurant.repository.RepoTaiKhoan;
import com.fake.Restaurant.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaiKhoanServiceImp implements TaiKhoanService {
    @Autowired
    private RepoTaiKhoan repoTaiKhoan;
    @Override
    public TaiKhoan tim_tai_khoan(String username) {
        Optional<TaiKhoan> taiKhoan=repoTaiKhoan.findById(username);
        if (taiKhoan.isEmpty()){
            return  null;
        }
        return taiKhoan.get();
    }
}
