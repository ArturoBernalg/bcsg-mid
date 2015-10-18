package com.bcsg;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.bcsg.bean.CreditCards;
import com.bcsg.utils.Deserialization;
import com.bcsg.utils.Utils;

/**
 * @author Arturo Bernal
 * 
 */
public class Bcsg {

	private static Deserialization des =new Deserialization();
	
	public static void main(String[] args) {

		   Locale.setDefault(new Locale("en", "US", "WIN"));

		   
		if(Arrays.asList(args).contains("?") || Arrays.asList(args).contains("help") || Arrays.asList(args).isEmpty())
		{
			
			System.out.println("-F or -f to indicate the file name to process");
			System.out.println("? or help to see the options\n");
			System.exit(1);			
		}
		 

		processCreditCards(args[0]);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void processCreditCards(String resource){
		
		des.setResource(resource);
		List<CreditCards> lstCreditCard= new  ArrayList<CreditCards>();
		lstCreditCard=(List<CreditCards>)(Object)des.start(new CreditCards());
		
		
		  String pattern = "MMM-yyyy";
		   DateFormat format = new SimpleDateFormat(pattern);
		
		   Collections.sort(lstCreditCard, new CreditCards());

		   
		if(lstCreditCard!=null){
			
			for(CreditCards creditCard:lstCreditCard)
			{
				
				System.out.println(creditCard.getBank());
				System.out.println(Utils.maskCardNumberRandom(creditCard.getCardNumber()));
				try {
					System.out.println(format.parse(creditCard.getExpiryDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}
		
		
	}
	
}
