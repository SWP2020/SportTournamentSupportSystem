package doan2020.SportTournamentSupportSystem.config;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class Const {
	public final static String DOMAIN = "http://192.168.1.127:3000"; //vuthon8
//	public final static String DOMAIN = "http://192.168.43.15:3000"; //LongSama
	
	@SuppressWarnings("deprecation")
	public final static Date DEFAULT_DATE = new Date(2020, 1, 1);
	
	public final static String DEFAULT_PLACE = "Hà Nội";
	
	public final static String TOKEN_HEADER = "Authorization";
	
	public final static String VIEWER = "viewer"; // Viewer
	
	public final static String MONITOR = "monitor";
	
	public final static String MANAGER = "manager";
	
	public final static String OWNER = "owner";
	

	public final static String BRANCH_CONFIG_FOLDER = "branch_config\\";
	
	public final static String IMAGE_FOLDER = "images\\";
	
	public final static String PATH_RESOURCE = "src\\main\\resources\\";
	
	public final static String AVATAR = "avatar";
	
	public final static String BACKGROUND = "background";

//	Constant for format
	
	public final static int SINGLE_ELIMINATION_FORMAT = 1;
	public final static int DOUBLE_ELIMINATION_FORMAT = 2;
	public final static int ROUND_ROBIN_FORMAT = 3;
	
//  status 
  
	public final static String UNSTARTED_STATUS = "unStarted";
	public final static String STARTED_STATUS = "started";
	public final static String FINISHED_STATUS = "finished";
	
	public final static String PENDING_STATUS = "pending";
	public final static String UNACCEPTED_STATUS = "unAccepted";
	public final static String ACCEPTED_STATUS = "Accepted";
  
//	Constant for naming match
	
	public final static String MATCH = "trận ";
	public final static String TABLE = "bảng ";
	public final static String TABLE_TOP = "Hạng ";
	public final static String WIN_MATCH = "Thắng ";
	public final static String LOSE_MATCH = "Thua ";
	public final static String SEED_NO = "Hạt giống số ";
	
	public final static String TABLE_NAMING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String WIN_BRANCH_NAMING = "A-";
	public final static String LOSE_BRANCH_NAMING = "B-";
	
//	Constant for role
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_USER = "ROLE_USER";
	

}
