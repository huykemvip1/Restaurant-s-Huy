package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.DanhGiaService;
import com.fake.Restaurant.service.DataCartService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class ControllerService {
    @Autowired
    private DanhGiaService danhGiaService;
    @Autowired
    private AnotherService anotherService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private DataCartService dataCartService;
    @GetMapping("/home")
    protected String goHome(Model model, HttpServletRequest request) throws IOException {
        log.info(request.getRemoteAddr());
       int soLuong=dataCartService.get_all_dataCart_sessionID(
                request.getRemoteAddr()
        ).size();
        model.addAttribute("soLuong",soLuong);


        //-------------
        List<DanhGia> danhGias=danhGiaService.ds_danh_gia_sp();
        danhGias.forEach(
                e->{
                    try {
                        anotherService.saveImage(
                                e.getKhachHang().getMonAn().getAnhMonAn(),
                                e.getKhachHang().getMonAn().getTen(),
                                "home"
                                );
                        e.setKhachHang(khachHangService.an_sdt(e.getKhachHang()));
                        e.setSoSao(danhGiaService.kich_thuoc_sao(e.getSoSao()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        model.addAttribute("danhGias",danhGias);
        return "home";
    }
    /*
    @GetMapping("/increase_comment")
    protected String increase(){
        if (VI_TRI_SP < danhGiaService.ds_danh_gia_all().size() - 2){
            VI_TRI_SP++;
        }
        return "redirect:/home#comment";
    }
    @GetMapping("/reduce_comment")
    protected String reduce(){
        if (VI_TRI_SP > 0){
            VI_TRI_SP--;
        }else {
            VI_TRI_SP=0;
        }
        return "redirect:/home#comment";
    }
    * */
}