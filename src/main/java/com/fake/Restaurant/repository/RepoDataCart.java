package com.fake.Restaurant.repository;

import com.fake.Restaurant.domain.DataCart;
import com.fake.Restaurant.domain.composite_key.DataCartID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepoDataCart extends JpaRepository<DataCart, DataCartID> {
    @Query(
            value = "select d from DataCart d where d.sessionId= :sessionId"
    )
    List<DataCart> find_all_dataCart(@Param("sessionId") String sessionId);


}
