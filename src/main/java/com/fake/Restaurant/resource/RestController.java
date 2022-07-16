package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDataCart;
import com.fake.Restaurant.service.DanhGiaService;
import com.fake.Restaurant.service.DatBanService;
import com.fake.Restaurant.service.DataCartService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@org.springframework.web.bind.annotation.RestController
@Slf4j
@RequestMapping("/api")
public class RestController {
    @Autowired
    private DataCartService dataCartService;

    @Autowired
    private DatBanService datBanService;
    @Autowired
    private KhachHangService khachHangService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> nhanData(@RequestBody DataCart[] chuoi,
                                           HttpServletRequest request){

        log.info("{}",chuoi.length);
        boolean check=dataCartService.luu_du_lieu(chuoi,request.getRemoteAddr());
        if (check == true){
           return ResponseEntity.ok("Success save Cart");
        }else{
           return ResponseEntity.badRequest().body("Fault save Cart");
        }

    }
    @GetMapping("/add/{maMonAn}")
    public ResponseEntity<String> nhanData(@PathVariable("maMonAn") String maMonAn,
                                           HttpServletRequest request){
        log.info(maMonAn);
        boolean check=dataCartService.luu_sp(maMonAn,request.getRemoteAddr());

        if (check == true){


            log.info("Success save : "
                    + maMonAn +
                    " -- sesionID : "
                    +request.getRemoteAddr());


            return ResponseEntity.ok("Success save : "
                    + maMonAn +
                    " -- sesionID : "
                    +request.getRemoteAddr());
        }else{


            log.info("Fault save : "
                    + maMonAn +
                    " -- sesionID : "
                    +request.getRemoteAddr());


            return ResponseEntity.badRequest().body("Fault save : "
                    + maMonAn +
                    " -- sesionID : "
                    +request.getRemoteAddr());
        }
    }
    @GetMapping("/ds/{gioAn}")
    @ResponseBody
    public ResponseEntity<List<DatBan>> getDatBansByGio(@PathVariable("gioAn") String gioAn){


        List<DatBan> datBans=datBanService.find_ban_trong(gioAn);
        log.info("{}",datBans);
        return ResponseEntity.ok(datBans);
    }
    @PostMapping(value = "/comment_data",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<KhachHang>> getData(@RequestBody KhachHang[] khachHang){
        List<KhachHang> kh=khachHangService.tim_khach_hang_ten_sdt(
                khachHang[0].getTen(),
                khachHang[0].getSdt()
        );

        if (kh == null){
            return ResponseEntity.ok(null);
        }else{
            return ResponseEntity.ok(kh);
        }
    }
}
