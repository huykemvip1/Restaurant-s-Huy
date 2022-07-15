package com.fake.Restaurant.domain;

import com.fake.Restaurant.domain.convert.ConvertByteToBlob;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "monan")
public class MonAn {
    @Id
    @GeneratedValue(generator = "id_mon_an")
    @GenericGenerator(name = "id_mon_an",
    strategy = "com.fake.Restaurant.domain.generator.GeneratorMonAn")
    @Column(name = "ma_mon_an")
    private String maMonAn;

    @Column(name = "ten_mon_an")
    private String ten;

    @Column(name = "anh_mon_an")
    @Convert(converter = ConvertByteToBlob.class)
    private byte[] anhMonAn;

    @Column(name = "tong_so_luong")
    private int tongSoLuong;

    @Column(name = "so_luong_sd")
    private int soLuongSd;

    // kiem tra xem co phai la san pham moi k
    @Getter
    @Column(name = "sp_moi")
    private Boolean spMoi;


    // Gia tien tren 1 san pham
    @Column(name = "gia_tien")
    private BigDecimal giaTien;

    @Column(name = "nguyen_lieu")
    private String nguyenLieu;

    @ManyToOne
    @JoinColumn(name = "id_loai_mon_an")
    private LoaiMonAn loaiMonAn;

}
