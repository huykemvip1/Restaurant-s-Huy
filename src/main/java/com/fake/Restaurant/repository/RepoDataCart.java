package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.composite_key.DataCartID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoDataCart extends JpaRepository<DataCart, DataCartID> {
    @Query(
            value = "select d from DataCart d where d.sessionId= :sessionId"
    )
    List<DataCart> find_all_dataCart(@Param("sessionId") String sessionId);

    DataCart deleteBySessionId(String sessionId);
}
