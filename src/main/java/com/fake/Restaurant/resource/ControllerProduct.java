package com.fake.Restaurant.resource;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.exception.ValueDoesNotExist;
import com.fake.Restaurant.service.DanhGiaService;
import com.fake.Restaurant.service.DataCartService;
import com.fake.Restaurant.service.MonAnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ControllerProduct {
    @Autowired
    private MonAnService monAnService;
    @Autowired
    private DanhGiaService danhGiaService;
    @Autowired
    private DataCartService dataCartService;
    @GetMapping("/product")
    private String homeSP(Model model, HttpServletRequest request) throws ValueDoesNotExist {


        int soLuong=dataCartService.get_all_dataCart_sessionID(
                request.getRemoteAddr()
        ).size();
        List<DataCart> dataCarts= dataCartService.get_all_dataCart_sessionID(request.getRemoteAddr());
        List<String> list=new ArrayList<>();
        for (DataCart dataCart: dataCarts){
            list.add(dataCart.getMaMonAn());
        }

        if (list.size() < 1){
            list.add("error");
        }
        model.addAttribute("list",list);
        model.addAttribute("soLuong",soLuong);


        //-------------
        model.addAttribute("spMoi",monAnService.sp_moi());
        model.addAttribute("spDoAnM",monAnService.sp_do_an_man());
        model.addAttribute("spDoAnN",monAnService.sp_do_an_nhe());
        model.addAttribute("spDoUong",monAnService.sp_do_uong());
        return "product";
    }

}
