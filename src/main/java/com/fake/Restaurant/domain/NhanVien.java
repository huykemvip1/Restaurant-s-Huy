package com.fake.Restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {
    @Id
    @Column(name = "ma_nhan_vien")
    private String maNhanVien;
    private String ten;

    @Column(name = "anh_nhan_vien")
    private byte[] anhNhanVien;

    private String sdt;

    private String mail;

    // Ngay thang nam sinh
    @Column(name = "sinh_nhat")
    private LocalDate sinhNhat;

    // Chung minh nhan dan
    private String cmnd;
    // Xac nhan cac khach hang da thanh toan
    @OneToMany
    private List<KhachHang> xacNhan;
}
