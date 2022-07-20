package com.fake.Restaurant.domain;

import com.fake.Restaurant.domain.composite_key.KhachHangID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(KhachHangID.class)
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(generator = "id_khach_hang")
    @GenericGenerator(name = "id_khach_hang",
    strategy = "com.fake.Restaurant.domain.generator.GeneratorKhachHang")
    @Column(name="ma_khach_hang")
    private String maKhachHang;
    @Id
    @Column(name = "ma_do_an")
    private String maDoAn;

    @Column(name = "ten_khach_hang")
    private String ten;

    @Column(name = "so_dien_thoai")
    private String sdt;

    private String email;

    // Ten the ngan hang
    @Column(name = "ten_the_nh")
    private String tenThe;

    @Column(name = "thoi_gian_dat")
    private LocalDateTime thoiGianDat;

    @Column(name = "ma_thanh_toan")
    private String maThanhToan;

    // Xac nhan thanh toan
    @Getter
    @Column(name = "xac_nhan_TT")
    private Boolean xacNhan;

    @Column(name = "so_luong")
    private int soLuong;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_do_an",insertable = false,updatable = false)
    private MonAn monAn;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "khach_hang_danh_gia",
            inverseJoinColumns = {
                    @JoinColumn(name = "ma_danh_gia")
            },
            joinColumns ={
                    @JoinColumn(name = "ma_do_an"),
                    @JoinColumn(name = "ma_khach_hang")

            }
    )
    private DanhGia danhGia;

    /*
    // Nguoi xac nhan thanh toan
    @ManyToOne
    private NhanVien nguoi_xac_nhan;

    * */

    // Dat ban hoac order mang ve

}
