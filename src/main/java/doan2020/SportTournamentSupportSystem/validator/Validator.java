package doan2020.SportTournamentSupportSystem.validator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Validator {

	public String formatDate(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			return strDate;
		} catch (Exception e) {
			return null;
		}
	}
	
	public int ConvertDobToAge(String dobEntity) {
		LocalDate dob = LocalDate.parse(dobEntity);
		LocalDate curDate = LocalDate.now();
		return Period.between(dob, curDate).getYears();
	}
}
