package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DatMangVe;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepoDatMangVe extends JpaRepository<DatMangVe,Long> {
}
