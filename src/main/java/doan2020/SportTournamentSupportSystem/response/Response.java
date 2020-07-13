package doan2020.SportTournamentSupportSystem.response;

import java.util.Map;

public class Response {

	private Map result;

	private Map error;

	public Response() {

	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public Map getError() {
		return error;
	}

	public void setError(Map error) {
		this.error = error;
	}

}
