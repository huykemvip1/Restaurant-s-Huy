package com.fake.Restaurant;

import com.fake.Restaurant.domain.DatMangVe;
import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.domain.LoaiMonAn;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.repository.*;
import com.fake.Restaurant.service.DatMangVeService;
import com.fake.Restaurant.service.KhachHangService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;
<<<<<<< HEAD
import java.util.concurrent.ConcurrentHashMap;
=======
>>>>>>> origin/master

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class RestaurantApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}
<<<<<<< HEAD

	private KhachHangService khachHangService;
	private DatMangVeService datMangVeService;

	@Override
	public void run(String... args) throws Exception {

=======
	private KhachHangService hangService;
	private RepoNhanVien repoNhanVien;
	private KhachHangService khachHangService;

	@Override
	public void run(String... args) throws Exception {
		Map<KhachHang,List<KhachHang>> khachHangListMap
				= khachHangService.tim_ds_khach_hang_chua_tt(0);
		Iterator<KhachHang> khachHangIterator=khachHangListMap.keySet().iterator();
		while (khachHangIterator.hasNext()){
			System.out.println(khachHangIterator.next().getMaKhachHang());
		}
		/*
		Map<KhachHang, List<KhachHang>> khachHangListMap=hangService.tim_ds_khach_hang_chua_tt(0);
		log.info("{}",khachHangListMap.values());
**/
		/*
		Optional<LoaiMonAn> loaiMonAn=repoLoaiMonAn.findById(3);
		File file=new File("F:/Anh_Code/Restaurant/so-da-phuc-bon-tu.jpg");
		byte[] bytes= Files.readAllBytes(file.toPath());
		MonAn monAn=MonAn.builder()
				.loaiMonAn(loaiMonAn.get())
				.giaTien(new BigDecimal(50000))
				.nguyenLieu("...")
				.ten("Soda Ph??c B???n T???")
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
				.ten("Nguy???n ????nh Huy")
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
		danhGia.setBinh_luan("M??n n??y ngon tuy???t v???i");
		danhGia.setSo_sao(5);

		* */
>>>>>>> origin/master
	}

}
