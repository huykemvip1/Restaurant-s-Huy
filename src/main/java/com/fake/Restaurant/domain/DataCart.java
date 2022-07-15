package com.fake.Restaurant.domain;

import com.fake.Restaurant.domain.composite_key.DataCartID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DataCartID.class)
@Table(name = "data_cart")
public class DataCart {
    @Id
    @Column(name = "session_id")
    private String sessionId;
    @Id
    @Column(name = "ma_mon_an")
    private String maMonAn;
    @Column(name = "so_luong")
    private int soLuong;
}
