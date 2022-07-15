package com.fake.Restaurant.domain.composite_key;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DatBanID implements Serializable {
    private String maBan;
    private String thoiGianSuDung;
}
