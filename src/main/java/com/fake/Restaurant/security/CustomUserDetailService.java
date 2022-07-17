package com.fake.Restaurant.security;


import com.fake.Restaurant.domain.TaiKhoan;
import com.fake.Restaurant.repository.RepoTaiKhoan;
import com.fake.Restaurant.service.TaiKhoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private RepoTaiKhoan repoTaiKhoan;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TaiKhoan> tk=repoTaiKhoan.findById(username);
        log.info("{}",tk.isEmpty());
        if (tk.isEmpty()){
            throw new UsernameNotFoundException("Tai khoan khong chinh xac");
        }else{
            TaiKhoan taiKhoan=tk.get();
            UserDetails userDetails= User.builder()
                    .username(taiKhoan.getTenTaiKhoan())
                    .password(taiKhoan.getMatKhau())
                    .authorities("ROLE_"+taiKhoan.getVaiTro())
                    .build();
            return userDetails;
        }

    }
}
