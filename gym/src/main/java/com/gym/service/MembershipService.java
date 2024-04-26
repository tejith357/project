package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.model.MemberShip;
import com.gym.repos.MemberShipRepository;

import jakarta.transaction.Transactional;

@Service
public class MembershipService {

	
	@Autowired
	private MemberShipRepository repo1;
	
	
	public void saveMembership(MemberShip mem) {

		repo1.save(mem);
	}
	
	public MemberShip viewMemberById( int id) {
		return repo1.findById(id).get();
		
	}
	@Transactional
	public void deleteMembership(int memId) {
	    repo1.deleteById(memId);
	}

	
	
	public void updateMembership(MemberShip mem) {
		repo1.save(mem);
		
	}

	
	public List<MemberShip> viewmembers() {

	 return repo1.findAll();

	}
}
