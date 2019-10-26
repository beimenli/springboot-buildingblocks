package com.stacksimplify.restservcies.springbootbuildingblocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservcies.springbootbuildingblocks.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
