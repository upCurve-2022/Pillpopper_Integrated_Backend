package com.Pill.Popper.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pill.Popper.dao.entity.Medicine;



@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{

	public List<Medicine> findByUserId(Long userId);
	
}