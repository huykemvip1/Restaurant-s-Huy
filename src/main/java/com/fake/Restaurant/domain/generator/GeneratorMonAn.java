package com.fake.Restaurant.domain.generator;

import com.fake.Restaurant.domain.MonAn;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@SuppressWarnings("unchecked")
public class GeneratorMonAn implements IdentifierGenerator {
    private String ma_mon_an="M";
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        MonAn monAn= (MonAn) object;
        String[] ten_mon_an=monAn.getTen().split(" ");
        int dem=0;
        while (dem < 6){
            ma_mon_an=ma_mon_an+ten_mon_an[dem].substring(0,1).toUpperCase();
            dem++;
            if (dem == ten_mon_an.length){
                break;
            }
        }
        log.info(ma_mon_an);
        List<MonAn> monAns= session.createQuery("select m from MonAn m")
                .list();
        Optional<MonAn> chon_mon_an=monAns.stream()
                .filter(e -> e.getMaMonAn().equals(ma_mon_an))
                .findAny();
        if (chon_mon_an.isEmpty()){
            return ma_mon_an;
        }else{
            return ma_mon_an.substring(0,ma_mon_an.length()-1)
                    + ten_mon_an[ten_mon_an.length-1].toUpperCase();
        }
    }
}
