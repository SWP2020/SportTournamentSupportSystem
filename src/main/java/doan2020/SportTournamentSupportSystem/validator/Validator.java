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
		int res = -1;
		try {
			System.out.println("ConvertDobToAge");
			System.out.println(dobEntity);
			LocalDate dob = LocalDate.parse(dobEntity);
			System.out.println("ConvertDobToAge: CP1");
			LocalDate curDate = LocalDate.now();
			System.out.println("ConvertDobToAge: CP2");
			res = Period.between(dob, curDate).getYears();
			System.out.println("ConvertDobToAge no exception");
		} catch (Exception e) {
			return -1;
		}
		
		return res;
	}
}
