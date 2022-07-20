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
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class RestaurantApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}

	private KhachHangService khachHangService;
	private DatMangVeService datMangVeService;

	@Override
	public void run(String... args) throws Exception {

	}

}
