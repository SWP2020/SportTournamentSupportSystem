package doan2020.SportTournamentSupportSystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.ViewUserInforDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IViewUserInforService;

@RestController
@RequestMapping("/view-user-infor")
public class ViewUserInfor {

	@Autowired
	private IViewUserInforService iViewUserInforService;
	@GetMapping("/overview")
	public ResponseEntity<Response> getUserInforOverview(@RequestBody ViewUserInforDtIn viewUserInforDtIn) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		
		try {
			response = iViewUserInforService.getUserInforOverView(viewUserInforDtIn);
			httpStatus = HttpStatus.OK;
		}catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/tournament")
	public ResponseEntity<Response> getUserInforTournament(@RequestBody ViewUserInforDtIn viewUserInforDtIn) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;

		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/team")
	public ResponseEntity<Response> getUserInforTeam(@RequestBody ViewUserInforDtIn viewUserInforDtIn) {
		Response response = new Response();
		HttpStatus httpStatus = null;

		httpStatus = HttpStatus.OK;

		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/news")
	public ResponseEntity<Response> getUserInforNews(@RequestBody ViewUserInforDtIn viewUserInforDtIn) {
		Response response = new Response();
		HttpStatus httpStatus = null;

		httpStatus = HttpStatus.OK;

		return new ResponseEntity<Response>(response, httpStatus);
	}
}
