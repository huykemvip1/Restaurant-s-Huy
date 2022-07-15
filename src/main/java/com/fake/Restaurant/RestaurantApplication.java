package com.fake.Restaurant;

import com.fake.Restaurant.domain.LoaiMonAn;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.repository.RepoLoaiMonAn;
import com.fake.Restaurant.repository.RepoMonAn;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}
	private RepoLoaiMonAn repoLoaiMonAn;
	private RepoMonAn repoMonAn;
	@Override
	public void run(String... args) throws Exception {

		/*
		Optional<LoaiMonAn> loaiMonAn=repoLoaiMonAn.findById(3);
		File file=new File("F:/Anh_Code/Restaurant/so-da-phuc-bon-tu.jpg");
		byte[] bytes= Files.readAllBytes(file.toPath());
		MonAn monAn=MonAn.builder()
				.loaiMonAn(loaiMonAn.get())
				.giaTien(new BigDecimal(50000))
				.nguyenLieu("...")
				.ten("Soda Phúc Bồn Tử")
				.tongSoLuong(10)
				.spMoi(false)
				.soLuongSd(3)
				.anhMonAn(bytes)
				.build();
		repoMonAn.save(monAn);

		* */

		/*
		Optional<MonAn> monAn=repoMonAn.findById("MBCX");
		KhachHang khachHang=KhachHang.builder()
				.ma_do_an(monAn.get().getMa_mon_an())
				.ten("Nguyễn Đình Huy")
				.email("huykemvip@gmail.com")
				.ma_thanh_toan("2134234234")
				.sdt("0329194466")
				.thoi_gian_dat(LocalDateTime.now())
				.monAn(monAn.get())
				.ten_the("TTTT111")
				.xac_nhan(false)
				.build();
		repoKhachHang.save(khachHang);

		//
		DanhGia danhGia=new DanhGia();
		danhGia.setBinh_luan("Món này ngon tuyệt vời");
		danhGia.setSo_sao(5);

		* */
	}

}
