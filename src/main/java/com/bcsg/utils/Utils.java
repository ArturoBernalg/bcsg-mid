package com.bcsg.utils;

import java.util.Random;

/**
 * @author Arturo Bernal
 * 
 */
public class Utils {

	/*
	 * Credit card randomly ordered manner
	 * 
	 * */
	
	public static String maskCardNumberRandom(String cardNumber) {

		String[] card=cardNumber.split("-");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(4);
		
		StringBuffer maskCard=new StringBuffer();
		for(int x=0;x<card.length;x++)
		{
			
			if(x!=randomInt)
				maskCard=maskCard.append(card[x].replaceAll("\\d","#")).append("-");
			else
				maskCard.append(card[x]).append("-");
		}
		
		return maskCard.toString().substring(0,maskCard.length()-1);
	
	}
	
	
}
