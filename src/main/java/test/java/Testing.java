package test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.bcsg.bean.CreditCards;
import com.bcsg.utils.Deserialization;
import com.bcsg.utils.Utils;

public class Testing {

	private Deserialization des = new Deserialization();

	private String PATTERN = "MMM-yyyy";
	private DateFormat FORMAT = new SimpleDateFormat(PATTERN, Locale.US);

	private static String MASK = "#";

	private String RESOURCE = "/test/java/resources/mid-test.csv";

	@Test
	public void readFromCVS() {
		URL url = this.getClass().getResource(RESOURCE);
		assertNotNull(url);
	}

	@Test
	public void chechOrder() {
		assertTrue(validateDateOrder());
	}

	@SuppressWarnings("unchecked")
	public boolean validateDateOrder() {
		Locale.setDefault(new Locale("en", "US", "WIN"));
		des.setResource(RESOURCE);
		List<CreditCards> lstCreditCard = new ArrayList<CreditCards>();
		lstCreditCard = (List<CreditCards>) (Object) des.start(new CreditCards());

		Collections.sort(lstCreditCard, new CreditCards());
		CreditCards nextCC = new CreditCards();

		for (CreditCards cc : lstCreditCard) {
			try {
				if (nextCC.getExpiryDate() != null) {
					if (FORMAT.parse(cc.getExpiryDate()).getTime() > (FORMAT.parse(nextCC.getExpiryDate()).getTime()))
						return false;

				}
			} catch (ParseException e) {
				return false;
			}

			nextCC = cc;
		}

		return true;
	}

	@Test
	public void validateMask() {

		assertTrue(checkMaskCard());

	}
	@SuppressWarnings("unchecked")
	public boolean checkMaskCard() {
		des.setResource(RESOURCE);
		List<CreditCards> lstCreditCard = new ArrayList<CreditCards>();
		lstCreditCard = (List<CreditCards>) (Object) des.start(new CreditCards());
		for (CreditCards cc : lstCreditCard) {
			if (Utils.maskCardNumberRandom(cc.getCardNumber()).contains(MASK))
				return true;

		}

		return false;

	}
}
