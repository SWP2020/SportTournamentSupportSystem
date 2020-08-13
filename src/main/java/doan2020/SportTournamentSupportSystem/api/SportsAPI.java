package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.SportConverter;
import doan2020.SportTournamentSupportSystem.converter.SportConverter;
import doan2020.SportTournamentSupportSystem.dto.SportDTO;
import doan2020.SportTournamentSupportSystem.dto.SportDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICommentService;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.ISportService;

@RestController
@CrossOrigin
@RequestMapping("/sports")
public class SportsAPI {

	@Autowired
	private SportConverter converter;

	@Autowired
	private ISportService service;

	@Autowired
	private ICompetitionService competitionService;

	@GetMapping("/getByTournamentId")
	public ResponseEntity<Response> getByTournamentId(
			@RequestParam(value = "tournamentId", required = false) Long tournamentId) {

		System.out.println("SportsAPI: getByTournamentId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<SportEntity> entities = new ArrayList<SportEntity>();
		Collection<SportDTO> dtos = new HashSet<SportDTO>();

		try {
			if (tournamentId == null) { // tournamentId null
				result.put("Sports", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param tournamentId");
			} else { // tournamentId not null

				Collection<CompetitionEntity> competitions = competitionService.findByTournamentId(tournamentId);
				
				Integer compSize = competitions.size();
				String strCompSize = compSize.toString();
				
				System.out.println("SportsAPI: getByTournamentId: competitions size: " + strCompSize);
				
				HashSet<Long> sportsId = new HashSet<>();

				for (CompetitionEntity comp : competitions) {

					sportsId.add(comp.getSport().getId());
				}
				
				for (Long sportId : sportsId) {
					SportDTO dto = converter.toDTO(service.findOneById(sportId));
					dtos.add(dto);
				}

				result.put("Sports", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
			}

			System.out.println("SportsAPI: getByTournamentId: no exception");
		} catch (Exception e) {
			System.out.println("SportsAPI: getgetByTournamentIdSport: has exception");
			result.put("Sports", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("SportsAPI: getByTournamentId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
