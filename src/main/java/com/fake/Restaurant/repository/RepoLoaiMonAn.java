package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.LoaiMonAn;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepoLoaiMonAn extends JpaRepository<LoaiMonAn,Integer> {
}
