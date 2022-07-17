package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @Column(name = "ma_nhan_vien")
    private String maNhanVien;
    
    @Column(name = "ho_va_ten")
    private String ten;

    @Column(name = "anh_nhan_vien")
    private byte[] anhNhanVien;

    @Column(name = "so_dien_thoai")
    private String sdt;

    private String mail;

    // Ngay thang nam sinh
    @Column(name = "sinh_nhat")
    private LocalDate sinhNhat;

    // Chung minh nhan dan
    private String cmnd;
    // Xac nhan cac khach hang da thanh toan
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nhan_vien")
    private List<KhachHang> xacNhan;

    @OneToOne
    @JoinColumn(name = "ten_tai_khoan")
    private TaiKhoan taiKhoan;
}
