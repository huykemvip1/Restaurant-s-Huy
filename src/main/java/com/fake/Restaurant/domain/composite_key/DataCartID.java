package com.fake.Restaurant.domain.composite_key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCartID implements Serializable {
    private String sessionId;
    private String maMonAn;
}
