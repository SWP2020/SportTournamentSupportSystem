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
	
	public final static String VIEWER = "viewer"; // Viewer
	
	public final static String MONITOR = "monitor";
	
	public final static String MANAGER = "manager";
	
	public final static String OWNER = "owner";
	
	public final static String RESOURCE_PATH = "src/main/resource/";
	
	public final static String BRANCH_CONFIG_FOLDER = "branch_config/";
}
