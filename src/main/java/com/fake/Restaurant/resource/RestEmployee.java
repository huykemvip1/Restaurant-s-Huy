package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class RestEmployee {
    @Autowired
    private KhachHangService khachHangService;

    @PostMapping(value = "/xac_nhan/{account}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<KhachHang> xacNhan(@RequestBody KhachHang khachHang,
                                             @PathVariable("account") String account){
       List<KhachHang> kh=  khachHangService.cap_nhat_khach_hang_ten_sdt_tg(
               khachHang.getTen(),khachHang.getSdt(),khachHang.getThoiGianDat()
       );
        return ResponseEntity.ok().build();
    }
}
