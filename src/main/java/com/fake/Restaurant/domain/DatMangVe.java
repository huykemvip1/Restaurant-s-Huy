package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dat_mang_ve")
public class DatMangVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stt;

    @Column(name = "ma_dat_do")
    private String maDatDo;

    @Column(name = "so_nha")
    private String soNha;

    @Column(name = "duong_pho")
    private String duongPho;


    private String phuong;

    private String quan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_khach_hang"),
            @JoinColumn(name = "id_do_an")
    })
    private KhachHang khachHang;
}
