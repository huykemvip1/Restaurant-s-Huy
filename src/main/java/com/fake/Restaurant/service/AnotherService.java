package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DatBan;
import com.fake.Restaurant.domain.KhachHang;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AnotherService {
    void saveImage(byte[] image,String ten_anh,String vitri) throws IOException;
    void deleteImage(String ten_anh,String vitri) throws IOException;
    void sendMessageWithAttachment(KhachHang khachHang, DatBan datBan);
}
