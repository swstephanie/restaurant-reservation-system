package com.sw.restaurant.dao;

import com.sw.restaurant.pojo.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable,String> {

    List<DiningTable> findAllByIdNotInAndCapacityBetween(List<String> table_id_list, int cap_lower, int cap_upper);

    //List<DiningTable> findAllByTable_idIsNotInAndCapacityBetween(List<String> table_id_list, int cap_lower, int cap_upper);
}
