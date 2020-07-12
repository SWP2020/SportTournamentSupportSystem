package doan2020.SportTournamentSupportSystem.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.UserDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.service.IUserService;


@RestController
@RequestMapping("/users")
public class UserAPI {
	@Autowired
	private IUserService userService;

	/* ---------------- GET ALL USER ------------------------ */
//	@GetMapping
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<UserDtOut>> getAllUser(@RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		Sort sortable = Sort.by("userID").ascending();
		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
		return new ResponseEntity<Collection<UserDtOut>>(userService.findAll(pageable), HttpStatus.OK);
	}

	/* ---------------- register NEW USER ------------------------ */
//	@PostMapping
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody UserDtIn user) {
		if (userService.add(user)) {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
		}
	}
}
