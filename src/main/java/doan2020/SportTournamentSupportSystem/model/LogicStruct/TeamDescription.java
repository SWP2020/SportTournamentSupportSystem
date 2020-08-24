package doan2020.SportTournamentSupportSystem.model.LogicStruct;

import java.io.Serializable;

import doan2020.SportTournamentSupportSystem.config.Const;

public class TeamDescription implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* Type:
	 * -1: unknown
	 * 0: seed
	 * 1: top x from a table
	 * 2: winner from a match in win branch (win branch)
	 * 3: winner from a match in lose branch (lose branch)
	 * 4: loser from a math in win branch (lose branch)
	 */
	Long descType;
	
	/*
	 * unit result:
	 * 0: Hạng
	 * 1: Thắng
	 * 2: Thua
	 * 
	 * 
	 * unit rank:
	 * 0: ""
	 * 1: "1"
	 * ...
	 * 
	 * unit type: bảng(bảng), trận(A, B)
	 * unit index: bảng(A, B, C,...) trận (1, 2, 3,...)
	 * 
	 * unit name: bảng A, bảng B, A-1, A-2,...
	 */
	String unitResult;
	Long unitRank;
	
	String unitType;
	int unitIndex;
	
	// unitName = unitType + unitIndex
	String unitName;
	
	// description = unitResult + unitRank + unitName
	String description;
	
	
	public TeamDescription() {
		super();
		this.descType = -1l;
		this.unitResult = "";
		this.unitRank = 0l;
		this.unitType = "";
		this.unitIndex = -1;
		this.unitName = unitType + unitIndex;
		this.description = unitResult + getUnitRankString() + unitName;
	}
	
	public TeamDescription(Long seed_no) { // for seed
		super();
		this.descType = 0l;
		this.unitResult = "";
		this.unitRank = 0l;
		this.unitType = Const.SEED_NO;
		this.unitIndex = seed_no.intValue();
		this.unitName = unitType + unitIndex;
		this.description = unitResult + getUnitRankString() + unitName;
	}
	
	public TeamDescription(int tableNo, Long rankNo) { // for table
		super();
		this.descType = 1l;
		this.unitResult = Const.TABLE_TOP;
		this.unitRank = rankNo;
		this.unitType = Const.TABLE;
		this.unitIndex = tableNo;
		this.unitName = unitType + Const.TABLE_NAMING.charAt(this.unitIndex);
		this.description = unitResult + getUnitRankString() + unitName;
	}
	
	public TeamDescription(Long descType, int matchNo) { // for match
		super();
		this.descType = descType;
		this.unitIndex = matchNo;
		this.unitRank = 0l;
		
		switch (descType.intValue()) {
		case 2: 
			this.unitResult = Const.WIN_MATCH;
			this.unitType = Const.WIN_BRANCH_NAMING;
			break;
		case 3: 
			this.unitResult = Const.WIN_MATCH;
			this.unitType = Const.LOSE_BRANCH_NAMING;
			break;
		case 4:
			this.unitResult = Const.LOSE_MATCH;
			this.unitType = Const.WIN_BRANCH_NAMING;
			break;
		default: // unknown
			this.descType = -1l;
			this.unitResult = "";
			this.unitRank = 0l;
			this.unitType = "";
			this.unitIndex = -1;
			break;
		}
		
		this.unitName = unitType + unitIndex;
		this.description = unitResult + getUnitRankString() + unitName;
	}
	
	public TeamDescription(Long descType, String unitResult, Long unitRank, String unitType, int unitIndex) {
		super();
		this.descType = descType;
		this.unitResult = unitResult;
		this.unitRank = unitRank;
		this.unitType = unitType;
		this.unitIndex = unitIndex;
		this.unitName = unitType + unitIndex;
		this.description = unitResult + getUnitRankString() + unitName;
	}

	public String getDescription() {
		return this.description;
	}
	
	private String getUnitRankString() {
		if (this.unitRank == null)
			return "";
		if (this.unitRank == 0l)
			return "";
		return this.unitRank.toString();
	}

	public Long getDescType() {
		return descType;
	}

	public void setDescType(Long descType) {
		this.descType = descType;
	}

	public String getUnitResult() {
		return unitResult;
	}

	public void setUnitResult(String unitResult) {
		this.unitResult = unitResult;
	}

	public Long getUnitRank() {
		return unitRank;
	}

	public void setUnitRank(Long unitRank) {
		this.unitRank = unitRank;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public int getUnitIndex() {
		return unitIndex;
	}

	public void setUnitIndex(int unitIndex) {
		this.unitIndex = unitIndex;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
	
}
