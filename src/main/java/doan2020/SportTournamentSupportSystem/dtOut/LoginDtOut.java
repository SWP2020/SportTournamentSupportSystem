package doan2020.SportTournamentSupportSystem.dtOut;

public class LoginDtOut {
	private String accessToken;
    private String tokenType = "Bearer";

    public LoginDtOut(String accessToken) {
        this.accessToken = accessToken;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public LoginDtOut(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
    
}
