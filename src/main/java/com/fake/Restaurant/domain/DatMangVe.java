package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany()
    @JoinTable(
            name = "khach_hang_dat_mang_ve",
            joinColumns = @JoinColumn(name = "ma_dat_do"),
            inverseJoinColumns = {
                    @JoinColumn(name = "ma_do_an"),
                    @JoinColumn(name = "ma_khach_hang")
            }
    )
    private List<KhachHang> khachHang;
}
