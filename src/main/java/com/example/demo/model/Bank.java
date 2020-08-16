package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Account")
public class Bank {
	   @Id
	   public int taxref;
	   public String accno;
	   public String datetime;
	   public int debit;
	   public String description;
	   public int runningbalance;
	   public int withdrawls;
	   
	  
	
	
	
	public Bank(int taxref, String accno, String datetime, int debit, String description, int runningbalance,
			int withdrawls) {
		super();
		this.taxref = taxref;
		this.accno = accno;
		this.datetime = datetime;
		this.debit = debit;
		this.description = description;
		this.runningbalance = runningbalance;
		this.withdrawls = withdrawls;
	}

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bank(List<Bank> records) {
		// TODO Auto-generated constructor stub
	}

	public int getTaxref() {
		return taxref;
	}

	public void setTaxref(int taxref) {
		this.taxref = taxref;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRunningbalance() {
		return runningbalance;
	}

	public void setRunningbalance(int runningbalance) {
		this.runningbalance = runningbalance;
	}

	public int getWithdrawls() {
		return withdrawls;
	}

	public void setWithdrawls(int withdrawls) {
		this.withdrawls = withdrawls;
	}

	@Override
	public String toString() {
		return "Bank [taxref=" + taxref + ", accno=" + accno + ", datetime=" + datetime + ", debit=" + debit
				+ ", description=" + description + ", runningbalance=" + runningbalance + ", withdrawls=" + withdrawls
				+ "]";
	}

	
	
	}
