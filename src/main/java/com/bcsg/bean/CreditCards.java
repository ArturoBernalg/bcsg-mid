package com.bcsg.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType()
public class CreditCards implements Comparator<CreditCards> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 3065320161665423982L;

	@CsvField(pos = 1)
	private String bank;
	@CsvField(pos = 2)
	private String cardNumber;
	@CsvField(pos = 3)
	private String expiryDateStr;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDateStr;
	}

	public void setExpiryDate(String expiryDateStr) {
		this.expiryDateStr = expiryDateStr;
	}

	

	public int compare(CreditCards arg0, CreditCards arg1) {
		 String pattern = "MMM-yyyy";
		   DateFormat format = new SimpleDateFormat(pattern);
		
		   long t1 = 0;
		   long t2 = 0;
		try {
			t1 = format.parse(arg0.getExpiryDate()).getTime();
		    t2 = format.parse(arg1.getExpiryDate()).getTime();
		} catch (ParseException e) {
		}
	    
		return Long.signum(t2 - t1);

		
	}

}
