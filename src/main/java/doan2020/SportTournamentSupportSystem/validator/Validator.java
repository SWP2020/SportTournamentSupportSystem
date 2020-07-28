package doan2020.SportTournamentSupportSystem.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Validator {

	public String formatDate(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String strDate = formatter.format(date);
			return strDate;
		} catch (Exception e) {
			return null;
		}
	}
}
