package com.fake.Restaurant.domain;

import com.fake.Restaurant.domain.composite_key.DatBanID;
import com.fake.Restaurant.domain.convert.ConvertLocalDatetimeToSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DatBanID.class)
@Table(name = "dat_ban")
public class DatBan implements Serializable {
    @Id
    @Column(name = "ma_ban")
    private String maBan;

    @Id
    @Column(name = "thoi_gian_dung")
    @Convert(converter = ConvertLocalDatetimeToSQL.class)
    private String thoiGianSuDung;

    @Column(name = "ten_ban")
    private String tenBan;

    @Column(name = "so_luong_nguoi")
    private int soLuongNguoi;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "khach_hang_dat_ban",
        joinColumns = {
                @JoinColumn(name = "ma_ban"),
                @JoinColumn(name = "thoi_gian_dung")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "ma_do_an"),
                @JoinColumn(name = "ma_khach_hang")
        }
    )
    private List<KhachHang> khachHang;
}
