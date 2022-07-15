package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danh_gia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_danh_gia")
    private long maDanhGia;

    @Column(name = "binh_luan")
    private String binhLuan;

    @Column(name = "so_sao")
    private double soSao;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "ma_khach_hang",referencedColumnName = "ma_khach_hang"),
            @JoinColumn(name = "ma_do_an",referencedColumnName = "ma_do_an")
    })
    private KhachHang khachHang;
}
