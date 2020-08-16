package com.example.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Bank;


public interface BankRepo extends JpaRepository<Bank,Integer> 
{

	List<Bank> findByAccno(String accno);
	


}
