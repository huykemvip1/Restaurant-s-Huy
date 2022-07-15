package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.MonAn;
import com.fake.Restaurant.exception.ValueDoesNotExist;
import com.fake.Restaurant.repository.RepoLoaiMonAn;
import com.fake.Restaurant.repository.RepoMonAn;
import com.fake.Restaurant.service.AnotherService;
import com.fake.Restaurant.service.MonAnService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class MonAnServiceImp implements MonAnService {
    @Autowired
    private RepoMonAn repoMonAn;

    @Autowired
    private RepoLoaiMonAn repoLoaiMonAn;
    @Autowired
    private AnotherService anotherService;

    // Ma id loai do
    protected int DO_AN_NHE=1;
    protected int DO_AN_MAN=2;
    protected int DO_UONG_DONG_CHAI=3;
    protected int DO_UONG_PHA_CHE=4;

    @Override
    public List<MonAn> sp_moi() {
        List<MonAn> list=repoMonAn.findAll().stream()
                .filter(e -> e.getSpMoi() == true)
                .collect(Collectors.toList());
        saveImageForProduct(list);
        return list;
    }

    @Override
    public List<MonAn> sp_do_uong() throws ValueDoesNotExist {
        List<MonAn> ds_do_uong=repoMonAn.findAllByLoaiMonAn(
                repoLoaiMonAn.findById(DO_UONG_DONG_CHAI)
                        .orElseThrow(() -> new ValueDoesNotExist("Loai SP Khong Ton Tai"))
        );
        ds_do_uong.addAll(repoMonAn.findAllByLoaiMonAn(
                repoLoaiMonAn.findById(DO_UONG_PHA_CHE)
                        .orElseThrow(() -> new ValueDoesNotExist("Loai SP Khong Ton Tai"))
        ));
        saveImageForProduct(ds_do_uong);
        return ds_do_uong;
    }

    @Override
    public List<MonAn> sp_do_an_nhe() throws ValueDoesNotExist {

        List<MonAn>list=repoMonAn.findAllByLoaiMonAn(
                repoLoaiMonAn.findById(DO_AN_NHE)
                        .orElseThrow(() -> new ValueDoesNotExist("Loai SP Khong Ton Tai"))
        );
        saveImageForProduct(list);
        return list;
    }

    @Override
    public List<MonAn> sp_do_an_man() throws ValueDoesNotExist {
        List<MonAn> list= repoMonAn.findAllByLoaiMonAn(
                repoLoaiMonAn.findById(DO_AN_MAN)
                        .orElseThrow(() -> new ValueDoesNotExist("Loai SP Khong Ton Tai"))
        );
        saveImageForProduct(list);
        return list;
    }

    @Override
    public void saveImageForProduct(List<MonAn> monAns) {
        monAns.forEach(monAn -> {
            try {
                anotherService.saveImage(monAn.getAnhMonAn(),monAn.getMaMonAn(),"product");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<MonAn> sp_da_chon(List<DataCart> dataCarts) throws ValueDoesNotExist {
        List<MonAn> monAns=new ArrayList<>();
        int countError=0;
        for(DataCart dataCart: dataCarts){
            Optional<MonAn> find=repoMonAn.findById(dataCart.getMaMonAn());
            if (find.isEmpty()){
                countError++;
            }else{
                monAns.add(find.get());
            }
        }
        if (countError >= 1){
            throw new ValueDoesNotExist("Product is not exist");
        }else{
            saveImageForProduct(monAns);
            return monAns;
        }
    }
}
