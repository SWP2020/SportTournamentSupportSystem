package doan2020.SportTournamentSupportSystem.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeAPI {

	@GetMapping
	public String test() {
		System.out.println("Home");
		return "Home";
	}
}
