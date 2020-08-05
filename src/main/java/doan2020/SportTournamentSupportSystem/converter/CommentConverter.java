package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.CommentDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.CommentDtOut;
import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IPostService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class CommentConverter {
	
//    @Autowired
//    private Validator validator;
//    
//    @Autowired
//    private IUserService userService;
//    
//    @Autowired
//    private IPostService postService;
//    
//    public CommentEntity toEntity(CommentDtIn commentDtIn) {
//    	CommentEntity commentEntity = new CommentEntity();
//    	
//    	if(commentDtIn.getAuthorId() != null) {
//    		UserEntity author = userService.findOneById(commentDtIn.getAuthorId());
//    		commentEntity.setAuthor(author);
//    	}
//    	if(commentDtIn.getContent() != null) {
//    		commentEntity.setContent(commentDtIn.getContent());
//    	}
//    	if(commentDtIn.getPostId() != null) {
//    		PostEntity postEntity = postService.findById(commentDtIn.getPostId());
//    		commentEntity.setPost(postEntity);
//    	}
//    	if(commentDtIn.getStatus() != null) {
//    		commentEntity.setStatus(commentDtIn.getStatus());
//    	}
//    	if(commentDtIn.getUrl() != null) {
//    		commentEntity.setUrl(commentDtIn.getUrl());
//    	}
//    	
//    	return commentEntity;
//    }
//    
//    public CommentEntity toEntity(CommentDtIn commentDtIn, CommentEntity commentEntity) {
//    	
//    	if(commentDtIn.getContent() != null) {
//    		commentEntity.setContent(commentDtIn.getContent());
//    	}
//    	if(commentDtIn.getStatus() != null) {
//    		commentEntity.setStatus(commentDtIn.getStatus());
//    	}
//    	if(commentDtIn.getUrl() != null) {
//    		commentEntity.setUrl(commentDtIn.getUrl());
//    	}
//    	return commentEntity;
//    }
//    
//    public CommentDtOut toDTO(CommentEntity commentEntity) {
//    	CommentDtOut commentDtOut = new CommentDtOut();
//    	try {
//    		
//    		commentDtOut.setAuthorId(commentEntity.getAuthor().getId());
//    		commentDtOut.setContent(commentEntity.getContent());
//    		commentDtOut.setId(commentEntity.getId());
//    		commentDtOut.setPostId(commentEntity.getPost().getId());
//    		commentDtOut.setStatus(commentEntity.getStatus());
//    		commentDtOut.setUrl(commentEntity.getStatus());
//    		
//    		System.out.println("true");
//    	}catch (Exception e) {
//			System.out.println("fail");
//			return null;
//		}
//    	return commentDtOut;
//    }

}
