package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.DanhGia;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.service.DatBanService;
import com.fake.Restaurant.service.DataCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
@Slf4j
public class ControllerComment {
    @Autowired
    private DataCartService dataCartService;
    @GetMapping
    public String showComment(Model model, HttpServletRequest request){
        int soLuong=dataCartService.get_all_dataCart_sessionID(
                request.getRemoteAddr()
        ).size();
        model.addAttribute("soLuong",soLuong);
        model.addAttribute("khachHang",new KhachHang());
        return "comment";
    }
    @PostMapping
    public String receiveData(@ModelAttribute("khachHang") KhachHang khachHang){
        log.info("{}",khachHang);
        return "redirect:/home";
    }
}
