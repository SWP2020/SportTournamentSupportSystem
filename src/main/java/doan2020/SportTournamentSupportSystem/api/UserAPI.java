package doan2020.SportTournamentSupportSystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IUserService;


@RestController
@RequestMapping("/users")
public class UserAPI {
	@Autowired
	private IUserService userService;

	/* ---------------- GET ALL USER ------------------------ */
//	@GetMapping
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Response> getAllUser(@RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		Sort sortable = Sort.by("userID").ascending();
		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
		return new ResponseEntity<Response>(userService.findAll(pageable), HttpStatus.OK);
	}


}
