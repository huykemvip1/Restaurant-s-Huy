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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_khach_hang"),
            @JoinColumn(name = "id_do_an")
    }
    )
    private KhachHang khachHang;
}
