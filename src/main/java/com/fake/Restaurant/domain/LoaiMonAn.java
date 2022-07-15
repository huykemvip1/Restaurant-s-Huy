package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loai_mon_an")
public class LoaiMonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai")
    private int maLoai;
    @Column(name = "ten_loai")
    private String ten;
}
