package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.CreateCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.EditCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.CompetitionDtOut;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.service.ISportService;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class CompetitionConverter {
	
	@Autowired
	private ITournamentService tournamentService;
	
	@Autowired
	private ISportService sportService;
	
	public CompetitionEntity toEntity(CreateCompetitionDtIn competitionDtIn) {
		CompetitionEntity competitionEntity = new CompetitionEntity();
		TournamentEntity tournamentEntity = new TournamentEntity();
		SportEntity sportEntity = new SportEntity();
		
		if(competitionDtIn.getName() != null) {
			competitionEntity.setName(competitionDtIn.getName());
		}
		if(competitionDtIn.getStatus() != null) {
			competitionEntity.setStatus(competitionDtIn.getName());
		}
//		if(competitionDtIn.getDescription() != null) {
//			competitionEntity.setDescription(competitionDtIn.getDescription());
//		}
		
		if(competitionDtIn.getTournamentID() != null) {
			tournamentEntity = tournamentService.findOneById(competitionDtIn.getTournamentID());
			
			if(tournamentEntity !=null) {
				competitionEntity.setTournament(tournamentEntity);
			}
		}
		
		if(competitionDtIn.getSportID() != null) {
			sportEntity = sportService.getSportbyId(competitionDtIn.getSportID());
			
			if(sportEntity !=null) {
				competitionEntity.setSport(sportEntity);
			}
		}
		return competitionEntity;
	}
	
	
	public CompetitionEntity toEntityUpdate(EditCompetitionDtIn competitionDtIn, CompetitionEntity competitionEntity) {
		TournamentEntity tournamentEntity = new TournamentEntity();
		SportEntity sportEntity = new SportEntity();
		
		if(competitionDtIn.getName() != null) {
			competitionEntity.setName(competitionDtIn.getName());
		}
		if(competitionDtIn.getStatus() != null) {
			competitionEntity.setStatus(competitionDtIn.getName());
		}
//		if(competitionDtIn.getDescription() != null) {
//			competitionEntity.setDescription(competitionDtIn.getDescription());
//		}
		
		if(competitionDtIn.getTournamentID() != null) {
			tournamentEntity = tournamentService.findOneById(competitionDtIn.getTournamentID());
			
			if(tournamentEntity !=null) {
				competitionEntity.setTournament(tournamentEntity);
			}
		}
		
		if(competitionDtIn.getSportID() != null) {
			sportEntity = sportService.getSportbyId(competitionDtIn.getSportID());
			
			if(sportEntity !=null) {
				competitionEntity.setSport(sportEntity);
			}
		}
		return competitionEntity;
	}
	
	
	public CompetitionDtOut toDTO(CompetitionEntity competitionEntity) {
		CompetitionDtOut competitionDtOut = new CompetitionDtOut();
		try {
		competitionDtOut.setCreatedby(competitionEntity.getCreatedby());
		competitionDtOut.setCreateddate(competitionEntity.getCreateddate());
		competitionDtOut.setModifiedby(competitionEntity.getModifiedby());
		competitionDtOut.setModifieddate(competitionEntity.getModifieddate());
		competitionDtOut.setName(competitionEntity.getName());
		competitionDtOut.setStatus(competitionEntity.getStatus());
		competitionDtOut.setId(competitionEntity.getId());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return competitionDtOut;
		
	}
}
