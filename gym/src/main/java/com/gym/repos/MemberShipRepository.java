package com.gym.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.MemberShip;
@Repository
public interface MemberShipRepository extends JpaRepository<MemberShip, Integer> {

	

}
