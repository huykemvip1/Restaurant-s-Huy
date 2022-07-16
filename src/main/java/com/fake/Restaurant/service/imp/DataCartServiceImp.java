package com.fake.Restaurant.service.imp;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.composite_key.DataCartID;
import com.fake.Restaurant.repository.RepoDataCart;
import com.fake.Restaurant.service.DataCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class DataCartServiceImp implements DataCartService {
    @Autowired
    private RepoDataCart repoDataCart;
    @Override
    public List<DataCart> get_all_dataCart_sessionID(String sessionId) {
        return repoDataCart.find_all_dataCart(sessionId);
    }

    @Override
    public int so_luong_sp(String sessionId) {
        return get_all_dataCart_sessionID(sessionId).size();
    }

    @Override
    public boolean luu_du_lieu(DataCart[] dataCarts,String sessionId) {
        List<DataCart> dataCartList=repoDataCart.find_all_dataCart(sessionId);
        List<DataCart> dataCartListNew= Arrays.stream(dataCarts).toList();
        dataCartListNew.forEach(e ->{
            e.setSessionId(sessionId);
        });
        if (dataCartList.size() == 0){
            return false;
        }else{
            repoDataCart.deleteAll(dataCartList);
            repoDataCart.saveAll(dataCartListNew);
            return true;
        }
    }

    @Override
    public boolean luu_sp(String maMonAn, String sessionId) {
        DataCart dataCart=new DataCart();
        if (maMonAn.equals("") ||
            maMonAn == null){
            return false;
        }else {
            dataCart.setSessionId(sessionId);
            dataCart.setMaMonAn(maMonAn);
            dataCart.setSoLuong(1);
            DataCart saveCart=repoDataCart.save(dataCart);
            log.info("{}",dataCart);
            if (saveCart == null){
                return false;
            }else {
                return true;
            }
        }
    }

    @Override
    public List<DataCart> findBySessionId(String sessionId) {
        return repoDataCart.find_all_dataCart(sessionId);
    }

    @Override
    public boolean xoa_mon_an(String maMonAn,String sessionId) {
        DataCartID dataCartID=new DataCartID();
        dataCartID.setMaMonAn(maMonAn);
        dataCartID.setSessionId(sessionId);
        Optional<DataCart> dataCart=repoDataCart.findById(dataCartID);
        if (dataCart.isEmpty()){
            return false;
        }else{
            repoDataCart.deleteById(dataCartID);
            return true;
        }

    }

    @Override
    public boolean xoa_sessionId(String sessionId) {
        List<DataCart> dataCarts=repoDataCart.find_all_dataCart(sessionId);

        if (dataCarts.size() < 1){
            return false;
        }else {
            repoDataCart.deleteAll(dataCarts);
            return true;
        }
    }
}
