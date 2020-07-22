package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.dtIn.ViewUserInforDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.ViewUserInforOverviewDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IViewUserInforService;

@Service
public class ViewUserInforService implements IViewUserInforService{

	@Autowired
	private UserRepository userRepository;
	@Override
	@Transactional
	public Response getUserInforOverView(ViewUserInforDtIn viewUserInforDtIn) {
		Response response = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		if(viewUserInforDtIn.getUserID() != null) {
			UserEntity userInfor = userRepository.findOneById(viewUserInforDtIn.getUserID());
			
			if(userInfor != null) {
			
			int numberOfTournament = userInfor.getTournaments().size();
			int numberOfTeam = userInfor.getTeams().size();
		
			ViewUserInforOverviewDtOut inforDtOut = new ViewUserInforOverviewDtOut();
			inforDtOut.setUserID(userInfor.getId());
			inforDtOut.setUsername(userInfor.getUsername());
			inforDtOut.setAvartar(userInfor.getAvatar());
			inforDtOut.setCreatedate(userInfor.getCreateddate());
			inforDtOut.setDob(userInfor.getDob());
			inforDtOut.setEmail(userInfor.getEmail());
			inforDtOut.setFirstname(userInfor.getFirstname());
			inforDtOut.setLastname(userInfor.getLastname());
			inforDtOut.setGender(userInfor.getGender());
			
			inforDtOut.setNumberOfTeam(numberOfTeam);
			inforDtOut.setNumberOfTournament(numberOfTournament);
			
			result.put("ViewUserInforOverview", inforDtOut);
			error.put("messageCode", "001");
			error.put("message", "ViewUserInforOverview successfull");
			}
			error.put("messageCode", "002");
			error.put("message", "ViewUserInforOverview fail");
		}else {
			error.put("messageCode", "002");
			error.put("message", "ViewUserInforOverview fail");
		}
		response.setError(error);
		response.setResult(result);
		return response;
	}

}
