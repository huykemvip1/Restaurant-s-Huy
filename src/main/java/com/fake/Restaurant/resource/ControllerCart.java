package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.*;
import com.fake.Restaurant.exception.ValueDoesNotExist;
import com.fake.Restaurant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class ControllerCart {
    @Autowired
    private DataCartService dataCartService;
    @Autowired
    private MonAnService monAnService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private DatBanService datBanService;
    @Autowired
    private DatMangVeService datMangVeService;
    @Autowired
    private AnotherService anotherService;

    @GetMapping("/sp")
    public String cartSanPham(Model model, HttpServletRequest request) throws ValueDoesNotExist {
        log.info(request.getRemoteAddr());
        List<MonAn> monAns=monAnService.sp_da_chon(
                dataCartService.findBySessionId(request.getRemoteAddr())
        );

        model.addAttribute("soLuong",
                dataCartService.so_luong_sp(request.getRemoteAddr()));
        model.addAttribute("monAns",monAns);

        return "cartProduct";
    }
    @GetMapping("/delete/{maMonAn}")
    public String deleteMonAn(@PathVariable("maMonAn") String maMonAn,HttpServletRequest request){
        boolean check=dataCartService.xoa_mon_an(maMonAn,request.getRemoteAddr());
        if (check == true){
            return "redirect:/cart/sp";
        }else{
            return "redirect:/error_page";
        }
    }

    @GetMapping("/information")
    public String addInformation(Model model,
                                 HttpServletRequest request) throws ValueDoesNotExist {

        List<MonAn> monAns=monAnService.sp_da_chon(
                dataCartService.findBySessionId(request.getRemoteAddr())
        );
        model.addAttribute("customer",new KhachHang());
        model.addAttribute("soLuong",
                dataCartService.so_luong_sp(request.getRemoteAddr()));
        return "cartInformation";
    }

    @PostMapping("/information")
    public String addedInformation(@ModelAttribute("customer") KhachHang khachHang,
                                   HttpServletRequest request) {

        List<DataCart> dataCarts=dataCartService.findBySessionId(request.getRemoteAddr());
     

        KhachHang khachHang1= khachHangService.luu_ttkh_tu_DataCart(dataCarts,khachHang);

        return "redirect:/cart/select/"+khachHang1.getMaKhachHang();
    }
    @GetMapping("/select/{maKhachHang}")
    public String selectAll(@PathVariable("maKhachHang") String maKhachHang,Model model){
        List<DatBan> datBans=datBanService.find_tg_trong();
        model.addAttribute("maKhacHang",maKhachHang);
        model.addAttribute("datBans",datBans);
        model.addAttribute("datBan",new DatBan());
        model.addAttribute("datMangVe",new DatMangVe());
        return "cartSelect";
    }
    @PostMapping("/book/{maKhachHang}")
    public String addBook(@PathVariable("maKhachHang") String maKhachHang,
                          @ModelAttribute("datBan") DatBan datBan){
        DatBan datBan1= datBanService.luu_dat_ban(datBan,maKhachHang);
        if(datBan1 != null){
            List<KhachHang> khachHang=khachHangService.tim_khach_hang_ma(maKhachHang);
            anotherService.sendMessageWithAttachment(khachHang.get(0),datBan1);
            return "redirect:/cart/success";
        }else {
            return "redirect:/page_error";
        }
    }
    @PostMapping("/order/{maKhachHang}")
    public String addOrder(@PathVariable("maKhachHang") String maKhachHang,
                          @ModelAttribute("datMangVe") DatMangVe datMangVe){

        boolean check= datMangVeService.luu_thong_tin_dat_ve(datMangVe,maKhachHang);
        if(check == true){
            List<KhachHang> khachHang=khachHangService.tim_khach_hang_ma(maKhachHang);
            anotherService.sendMessageWithAttachment(khachHang.get(0),null);
            return "redirect:/cart/success";
        }else {
            return "redirect:/page_error";
        }
    }

    @GetMapping("/success")
    public String waitConfirm(HttpServletRequest request){
        dataCartService.xoa_sessionId(request.getRemoteAddr());
       return "success";
    }
}
