package com.fake.Restaurant.domain.generator;

import com.fake.Restaurant.domain.KhachHang;
import com.fake.Restaurant.exception.AlreadyExist;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class GeneratorKhachHang implements IdentifierGenerator {
    private String prefix="KH";
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
            throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {
        long suffix=0;
        int check=0;
        Connection connection=session.connection();
        List<KhachHang> khachHangs=new ArrayList<>();
        try {
            ResultSet resultSet= connection
                    .prepareStatement("select * from khach_hang")
                    .executeQuery();
            while (resultSet.next()){
                KhachHang khachHang=new KhachHang();
                khachHang.setMaKhachHang(resultSet.getString("ma_khach_hang"));
                khachHang.setTen( resultSet.getString("ten_khach_hang"));
                khachHang.setSdt( resultSet.getString("so_dien_thoai"));
                khachHangs.add(khachHang);
            }

            khachHangs.stream()
                    .sorted(new Comparator<KhachHang>() {
                        @Override
                        public int compare(KhachHang o1, KhachHang o2) {
                            return o1.getMaKhachHang().compareTo(o2.getMaKhachHang());
                        }
                    });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        KhachHang khachHang= (KhachHang) object;

        if (khachHangs.size() <1){

        }else{
            boolean kt=true;

            for (int i=0;i<khachHangs.size()-1;i++){
                long ele_1=Long.parseLong(
                        khachHangs.get(i).getMaKhachHang().substring(
                                "KH".length(),
                                khachHangs.get(i).getMaKhachHang().length()
                        )
                );
                long ele_2=Long.parseLong(
                        khachHangs.get(i+1).getMaKhachHang().substring(
                                "KH".length(),
                                khachHangs.get(i+1).getMaKhachHang().length()
                        )
                );
                if (ele_1 - ele_2 > 1){
                    suffix=ele_1;
                    kt=false;
                    break;
                }
            }
            if (kt == true){
                suffix=Long.parseLong(
                        khachHangs.get(khachHangs.size()-1).getMaKhachHang().substring(
                                "KH".length(),
                                khachHangs.get(khachHangs.size()-1).getMaKhachHang().length()
                        )
                );
            }

        }
        return prefix+(suffix+1);
        }
    }

