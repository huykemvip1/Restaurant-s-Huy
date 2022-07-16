package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.repository.RepoDataCart;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.DatBanService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AnotherServiceImp implements AnotherService {
    private double KICH_THUOC_SAO=30;


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RepoDataCart repoDataCart;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private DatBanService datBanService;


    @Override
    public void saveImage(byte[] image,String ten_anh,String vitri) throws IOException {
        Path path=Path.of("src/main/resources/static/photos/memory-"+vitri+"/"+ten_anh+".jpg");
        Files.write(path,image);
    }

    @Override
    public void deleteImage(String ten_anh,String vitri) throws IOException {
        Path path=Path.of("src/main/resources/static/photos/memory-"+vitri+"/"+ten_anh+".jpg");
        Files.delete(path);
    }


    @Override
    public void sendMessageWithAttachment(KhachHang khachHang, DatBan datBan) {
        log.info("{}",datBan);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        List<KhachHang> khachHangs=khachHangService.tim_khach_hang_ma(khachHang.getMaKhachHang());
        simpleMailMessage.setFrom("huyjavacode@gmail.com");
        simpleMailMessage.setTo(khachHang.getEmail());
        simpleMailMessage.setSubject("Thông báo từ Restaurant's huy");
        if(datBan != null){
            simpleMailMessage.setText(
                    "Thông tin khách hàng \n"+
                            "Họ và tên : "+khachHang.getTen()+"\n"+
                            "Số điện thoại: "+khachHang.getSdt()+"\n"+
                            "Ngày đặt: "+khachHang.getThoiGianDat()+"\n"+
                    "-------------------------"+"\n"+
                            "Thông tin bàn đã đặt"+"\n"+
                            "Bàn : "+datBan.getTenBan()+" ---- "+
                            "Thời gian ăn : "+datBan.getThoiGianSuDung()

            );
        }else{
            simpleMailMessage.setText(
                    "Thông tin khách hàng \n"+
                            "Họ và tên : "+khachHang.getTen()+"\n"+
                            "Số điện thoại: "+khachHang.getSdt()+"\n"+
                            "Ngày đặt: "+khachHang.getThoiGianDat()+"\n"+
                    "-------------------------"+"\n"+
                            "Món ăn đã đặt : "+khachHangs.toString()
            );
        }
        javaMailSender.send(simpleMailMessage);
    }


}
