package com.example.demo.controller;
import com.example.demo.dao.BankRepo;
import com.example.demo.model.Bank;
import com.example.demo.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = {"http://localhost:8181", "http://localhost:8088"})
public class AccountController {
	
	
	@Autowired
	BankRepo repo;
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		return mv;	
	}
	
	@GetMapping("/getAccount")
	public ModelAndView getAccount(){
		List<Bank> records = repo.findAll();
		int i;
		ModelAndView mv = new ModelAndView("details");
		
		for(i=1;i<records.size();i++) {
			List<Bank> current = repo.findByAccno(records.get(i).accno);
			current.get(0).runningbalance = current.get(0).runningbalance - current.get(0).withdrawls+current.get(0).debit;
			for(i=1;i<current.size();i++) {
				int val = current.get(i-1).runningbalance;
				current.get(i).runningbalance = val-current.get(i).withdrawls+current.get(i).debit;
			}
		}
		Bank bank = new Bank(records);
		repo.saveAll(records);
		mv.addObject(bank);
	
		return mv;
	}
	
	
		
		
		@RequestMapping("index")
		public ModelAndView Index() {
			ModelAndView mv = new ModelAndView("index");
			return mv;
		}
		
		
		
	/* @PostMapping(value = "home/upload", consumes = "text/csv")
	    public String uploadSimple(@RequestBody InputStream body) throws IOException {
	        repo.saveAll(CsvUtils.read(Bank.class, body));
	        return "Repo saved";
	    }*/
	 @PostMapping(value = "home/upload", consumes = "multipart/form-data")
	    public String uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
	        repo.saveAll(CsvUtils.read(Bank.class, file.getInputStream()));
	        return "details saved";
	    }


	   
	   
	}
