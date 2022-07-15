package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.LoaiMonAn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoLoaiMonAn extends JpaRepository<LoaiMonAn,Integer> {
}
