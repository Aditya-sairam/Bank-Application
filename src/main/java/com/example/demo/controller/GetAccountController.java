package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
 
import com.example.demo.dao.BankRepo;
import com.example.demo.model.Bank;


@Controller
@CrossOrigin(origins = {"http://localhost:8181", "http://localhost:8088"})
public class GetAccountController {

	@Autowired
	BankRepo repo;
	
	public String accountno;
	
	@RequestMapping("bank/home/getByNum")
	public String  getByNum(@PathVariable("accno") @RequestParam String accno,RedirectAttributes redirectAttrs) {
		//List<Bank> account = repo.findByAccno(accno);
		
		redirectAttrs.addAttribute("accno",accno).addFlashAttribute("message", "Account created!");
		return "redirect:/bank/home/dispByNum/{accno}";
	}
	@RequestMapping("bank/home/dispByNum/{accno}")
	public String dispByNum(ModelMap mp,@PathVariable("accno")  String accno) {
		List<Bank> account = repo.findByAccno(accno);
		mp.put("account", account);
		accountno = accno;
		return "table";
		
		
	}
	
	
	public void writeObjectToCSV(PrintWriter writer,List<Bank> account) {
		String accno = accountno;
		List<Bank> accounts = repo.findByAccno(accno);
		
		 try (
			        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
			                      .withHeader("taxref", "accno", "datetime","debit","description","runningbalance","withdrawls"));
			    ) {
			      for(int i=0;i<accounts.size();i++) {
			        List<Object> data = Arrays.asList(
			            accounts.get(i).taxref,
			            accounts.get(i).accno,
			            accounts.get(i).datetime,
			            accounts.get(i).debit,
			            accounts.get(i).description,
			            accounts.get(i).runningbalance,
			            accounts.get(i).withdrawls
			          );
			        
			        csvPrinter.printRecord(data);
			      }
			      csvPrinter.flush();
			    } catch (Exception e) {
			      System.out.println("Writing CSV error!");
			      e.printStackTrace();
			    }
	}
	@GetMapping("downloadcsv/{accountno}/account.csv")
	  public void downloadCSV(HttpServletResponse response) throws IOException{
	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; file=account.csv");
	    
	    List<Bank> accounts = (List<Bank>) repo.findByAccno(accountno); 
	    writeObjectToCSV(response.getWriter(), accounts);
	  }
		 	
	@RequestMapping("bank/home/getallAccount")
	public String getallAccounts(ModelMap mp) {
		List<Bank> accounts = repo.findAll();
		List<Bank> records = repo.findAll();
		int i,j;
		for(i=0;i<accounts.size();i++) {
			List<Bank> record = repo.findByAccno(accounts.get(i).accno);
			record.get(0).runningbalance = record.get(0).runningbalance-record.get(0).withdrawls+record.get(0).debit;
			for(j=1;j<record.size();j++) {
				record.get(j).runningbalance = record.get(j-1).runningbalance-record.get(j).withdrawls+record.get(j).debit;
			}
		}
		
		
			
		repo.saveAll(accounts);
		mp.put("accounts",accounts);
		return "details";
	}
		
		@GetMapping("/getAccounts")
		public String getAccounts(ModelMap mp) {
			List<Bank> accounts = repo.findAll();
			List<Bank> records = repo.findAll();
			int i;
			
			
			for(i=1;i<records.size();i++) {
				List<Bank> current = repo.findByAccno(records.get(i).accno);
				current.get(0).runningbalance = current.get(0).runningbalance - current.get(0).withdrawls+current.get(0).debit;
				for(i=1;i<current.size();i++) {
					int val = current.get(i-1).runningbalance;
					current.get(i).runningbalance = val-current.get(i).withdrawls+current.get(i).debit;
				}
			}
			mp.put("accounts",accounts);
			return "details1";
		
	}
	
}
