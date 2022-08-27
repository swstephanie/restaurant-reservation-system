package com.sw.restaurant.dao;

import com.sw.restaurant.pojo.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable,String> {
}
