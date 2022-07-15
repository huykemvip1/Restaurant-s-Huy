package com.fake.Restaurant.domain.composite_key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangID implements Serializable {
    private String maKhachHang;
    private String maDoAn;
}
