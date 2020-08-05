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

@Component
public class CompetitionConverter {
	
//	@Autowired
//	private ITournamentService tournamentService;
//	
//	@Autowired
//	private ISportService sportService;
//	
//	public CompetitionEntity toEntity(CreateCompetitionDtIn competitionDtIn) {
//		CompetitionEntity competitionEntity = new CompetitionEntity();
//		TournamentEntity tournamentEntity = new TournamentEntity();
//		SportEntity sportEntity = new SportEntity();
//		
//		if(competitionDtIn.getName() != null) {
//			competitionEntity.setName(competitionDtIn.getName());
//		}
//		if(competitionDtIn.getStatus() != null) {
//			competitionEntity.setStatus(competitionDtIn.getName());
//		}
////		if(competitionDtIn.getDescription() != null) {
////			competitionEntity.setDescription(competitionDtIn.getDescription());
////		}
//		
//		if(competitionDtIn.getTournamentID() != null) {
//			tournamentEntity = tournamentService.findOneById(competitionDtIn.getTournamentID());
//			
//			if(tournamentEntity !=null) {
//				competitionEntity.setTournament(tournamentEntity);
//			}
//		}
//		
//		if(competitionDtIn.getSportID() != null) {
//			sportEntity = sportService.getSportbyId(competitionDtIn.getSportID());
//			
//			if(sportEntity !=null) {
//				competitionEntity.setSport(sportEntity);
//			}
//		}
//		return competitionEntity;
//	}
//	
//	
//	public CompetitionEntity toEntityUpdate(EditCompetitionDtIn competitionDtIn, CompetitionEntity competitionEntity) {
//		TournamentEntity tournamentEntity = new TournamentEntity();
//		SportEntity sportEntity = new SportEntity();
//		
//		if(competitionDtIn.getName() != null) {
//			competitionEntity.setName(competitionDtIn.getName());
//		}
//		if(competitionDtIn.getStatus() != null) {
//			competitionEntity.setStatus(competitionDtIn.getName());
//		}
//		
//		if(competitionDtIn.getDescription() != null) {
//			competitionEntity.setDescription(competitionDtIn.getDescription());
//		}
//		
//		if(competitionDtIn.getTournamentID() != null) {
//			tournamentEntity = tournamentService.findOneById(competitionDtIn.getTournamentID());
//			
//			if(tournamentEntity !=null) {
//				competitionEntity.setTournament(tournamentEntity);
//			}
//		}
//		
//		if(competitionDtIn.getSportID() != null) {
//			sportEntity = sportService.getSportbyId(competitionDtIn.getSportID());
//			
//			if(sportEntity !=null) {
//				competitionEntity.setSport(sportEntity);
//			}
//		}
//		return competitionEntity;
//	}
//	
//	
//	public CompetitionDtOut toDTO(CompetitionEntity competitionEntity) {
//		CompetitionDtOut competitionDtOut = new CompetitionDtOut();
//		try {
//	    competitionDtOut.setDescription(competitionEntity.getDescription());
//	    System.out.println("pass1");
//	    competitionDtOut.setGroupStage(competitionEntity.getGroupStage());
//	    System.out.println("pass2");
//	    competitionDtOut.setGroupStageFormatId(competitionEntity.getGroupStageFormat().getId());
//	    System.out.println("pass3");
//	    competitionDtOut.setMainFormatId(competitionEntity.getMainFormat().getId());
//	    System.out.println("pass4");
//	    competitionDtOut.setSportId(competitionEntity.getSport().getId());
//	    System.out.println("pass5");
//	    competitionDtOut.setTournamentId(competitionEntity.getTournament().getId());
//	    System.out.println("pass6");
//		competitionDtOut.setName(competitionEntity.getName());
//		System.out.println("pass7");
//		competitionDtOut.setStatus(competitionEntity.getStatus());
//		System.out.println("pass8");
//		competitionDtOut.setId(competitionEntity.getId());
//		System.out.println("pass9");
//		}catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("fail");
//		}
//		
//		return competitionDtOut;
//		
//	}
}
