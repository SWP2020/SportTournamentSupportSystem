package doan2020.SportTournamentSupportSystem.config;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class Const {
	
	public final static String DOMAIN = "http://192.168.1.127:3000"; //vuthon8
//	public final static String DOMAIN = "http://192.168.43.15:3000"; //LongSama
//	public final static String DOMAIN = "http://172.20.10.6:3000"; // Nhan's 11
	
	@SuppressWarnings("deprecation")
	public final static Date DEFAULT_DATE = new Date(2020, 1, 13, 7, 0, 0);
	public final static String DEFAULT_DATE_STRING = "2020-01-13 07:00:00";
	
	public final static String DEFAULT_PLACE = "Gầm cầu Long Biên";
	
	public final static String TOKEN_HEADER = "Authorization";
	
	public final static String VIEWER = "viewer"; // Viewer
	
	public final static String MONITOR = "monitor";
	
	public final static String MANAGER = "manager";
	
	public final static String OWNER = "owner";
	

	public final static String COMPETITION_FILESYSTEM = "competitions\\";
	public final static String COMPETITION_FOLDER_NAMING = "comp_";
	public final static String COMPETITION_SCHEDULING = "schedule.dat";
	public final static String COMPETITION_TEAM_PLAYERS_NAMING = "team_";
	public final static String FILE_EXTENDED = ".dat";
	
	public final static String IMAGE_FOLDER = "images\\";
	
	public final static String PATH_RESOURCE = "src\\main\\resources\\";
	
	public final static String AVATAR = "avatar";
	
	public final static String BACKGROUND = "background";

//	Constant for format
	
	public final static String SINGLE_ELIMINATION_FORMAT = "Single Elimination"; 
	public final static String DOUBLE_ELIMINATION_FORMAT = "Double Elimination";
	public final static String ROUND_ROBIN_FORMAT = "Round Robin";
	public final static String ANOTHER_FORMAT = "Unknown";
	
//	user status
	public final static String STATUS_ACTIVE = "active";
	public final static String STATUS_DEACTIVE = "deactive";
	
	public final static String STATUS_DELETED = "deleted";
	
//  status tournament
  
	public final static String TOURNAMENT_STATUS_INITIALIZING = "initializing";
	public final static String TOURNAMENT_STATUS_REGISTRATION_OPENING = "opening";
	public final static String TOURNAMENT_STATUS_PROCESSING = "processing";
	public final static String TOURNAMENT_STATUS_STOPPED = "stopped";
	public final static String TOURNAMENT_STATUS_FINISHED = "finished";
  
//	Constant for naming match
	
	public final static String ROUND_ROBIN_AWAY = " lượt về";
	public final static String ROUND_ROBIN_HOME = " lượt đi";
	public final static String WIN_BRANCH = " nhánh thắng";
	public final static String LOSE_BRANCH = " nhánh thua";
	public final static String SUMMARY_FINAL_BRANCH = "Chung kết tổng";
	
	public final static String MATCH = " trận ";
	public final static String ROUND = "Vòng ";
	public final static String TABLE = " bảng ";
	public final static String TABLE_TOP = "Hạng ";
	public final static String WIN_MATCH = "Thắng ";
	public final static String LOSE_MATCH = "Thua ";
	public final static String SEED_NO = "Hạt giống số ";
	
	public final static String FINAL = "Chung kết";
	public final static String SEMIFINAL = "Bán kết";
	public final static String QUARTERFINAL = "Tứ kết";
	public final static String THIRD_PLACE = "Tranh hạng 3";
	
	
	public final static String TABLE_NAMING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String SINGLE_BRACKET_NAMING = "M-";
	public final static String ROUND_ROBIN_MATCH_NAMING = "M-"; //"Trận ";
	public final static String WIN_BRANCH_NAMING = "W-";
	public final static String LOSE_BRANCH_NAMING = "L-";
	public final static String SUMMARY_FINAL = "Final-";
	
//	Constant for role
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_USER = "ROLE_USER";
	
//	Constant for team status
	public final static String TEAM_STATUS_PENDING = "pending";
	public final static String TEAM_STATUS_JOINED = "joined";
	public final static String TEAM_STATUS_REJECTED = "rejected";
	
//	report type
	public static final String REPORT_VIOLATION = "violation"; // report tournament to admin
	public static final String REPORT_FRAUD = "fraud"; // report fraud to tournament manager
	public static final String REPORT_SYSTEM_ERROR = "syserror"; // report system error to admin
	
}
