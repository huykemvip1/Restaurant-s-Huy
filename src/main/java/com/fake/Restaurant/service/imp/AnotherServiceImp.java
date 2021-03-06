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

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        List<KhachHang> khachHangs=khachHangService.tim_khach_hang_ma(khachHang.getMaKhachHang());
        simpleMailMessage.setFrom("huyjavacode@gmail.com");
        simpleMailMessage.setTo(khachHang.getEmail());
        simpleMailMessage.setSubject("Th??ng b??o t??? Restaurant's huy");
        if(datBan != null){
            simpleMailMessage.setText(
                    "Th??ng tin kh??ch h??ng \n"+
                            "H??? v?? t??n : "+khachHang.getTen()+"\n"+
                            "S??? ??i???n tho???i: "+khachHang.getSdt()+"\n"+
                            "Ng??y ?????t: "+khachHang.getThoiGianDat()+"\n"+
                    "-------------------------"+"\n"+
                            "Th??ng tin b??n ???? ?????t"+"\n"+
                            "B??n : "+datBan.getTenBan()+" ---- "+
                            "Th???i gian ??n : "+datBan.getThoiGianSuDung()+"\n \n"+
                            "M???i b???n thanh to??n v???i t??i kho???n n??y"+
                            "T??n t??i kho???n : NGUYEN DINH HUY \n"+
                            "Ng??n h??ng: BIDV \n"+
                            "S??? TK: 21510002641437"

            );
        }else{
            String moAns="";
            for(KhachHang kh: khachHangs){
                moAns+="\n"+kh.getMonAn().getTen();
            }
            simpleMailMessage.setText(
                    "Th??ng tin kh??ch h??ng \n"+
                            "H??? v?? t??n : "+khachHang.getTen()+"\n"+
                            "S??? ??i???n tho???i: "+khachHang.getSdt()+"\n"+
                            "Ng??y ?????t: "+khachHang.getThoiGianDat()+"\n"+
                    "-------------------------"+"\n"+
                            "M??n ??n ???? ?????t : "+moAns+"\n \n"+
                            "M???i b???n thanh to??n v???i t??i kho???n n??y"+
                            "T??n t??i kho???n : NGUYEN DINH HUY \n"+
                            "Ng??n h??ng: BIDV \n"+
                            "S??? TK: 21510002641437"
            );
        }
        javaMailSender.send(simpleMailMessage);
    }


}
