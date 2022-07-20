package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/employee")
public class ControllerEmployee {
    private int KHONG_SAP_XEP=0;
    private int SAP_XEP_TG_MOI=1;
    private int SAP_XEP_TG_CU=2;
    @Autowired
    private KhachHangService khachHangService;
    @GetMapping("/confirm")
    public String confirm(Model model){
        Map<KhachHang, List<KhachHang>> map= khachHangService.tim_ds_khach_hang_chua_tt(KHONG_SAP_XEP);

        model.addAttribute("map",map);
        return "employeeConfirm";
    }
}
