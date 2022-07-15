package com.fake.Restaurant.service;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.exception.ValueDoesNotExist;

import java.util.List;

public interface MonAnService {
    List<MonAn> sp_moi();
    List<MonAn> sp_do_uong() throws ValueDoesNotExist;
    List<MonAn> sp_do_an_nhe() throws ValueDoesNotExist;
    List<MonAn> sp_do_an_man() throws ValueDoesNotExist;
    void saveImageForProduct(List<MonAn> monAns);
    List<MonAn> sp_da_chon(List<DataCart> dataCarts) throws ValueDoesNotExist;

}
