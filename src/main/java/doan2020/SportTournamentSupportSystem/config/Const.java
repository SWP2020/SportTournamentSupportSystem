package doan2020.SportTournamentSupportSystem.config;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class Const {
	public final static String DOMAIN = "http://192.168.1.127:3000"; //vuthon8
	
	@SuppressWarnings("deprecation")
	public final static Date DEFAULT_DATE = new Date(2020, 1, 1);
	
	public final static String DEFAULT_PLACE = "Hà Nội";
	
	public final static String TOKEN_HEADER = "Authorization";
	
	public final static String NONE = "none";
	
	public final static String ONLY_DELETE = "only delete";
	
	public final static String ONLY_EDIT = "only edit";
	
	public final static String DELETE_AND_EDIT = "edit and delete";
}
