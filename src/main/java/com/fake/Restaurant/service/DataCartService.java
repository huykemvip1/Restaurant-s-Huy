package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DataCart;

import java.util.List;

public interface DataCartService {

     List<DataCart> get_all_dataCart_sessionID(String sessionId);

     int so_luong_sp(String sessionId);
     boolean luu_du_lieu(DataCart[] dataCarts,String sessionId);
     boolean luu_sp(String maMonAn,String sessionId);
     List<DataCart> findBySessionId(String sessionId);
     boolean xoa_mon_an(String maMonAn,String sessionId);
     boolean xoa_sessionId(String sessionId);
}
