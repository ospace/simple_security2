package com.tistory.ospace.simplesecurity2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.tistory.ospace.simplesecurity2.entity.Notice;
import com.tistory.ospace.simplesecurity2.repository.NoticeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSecurity2ApplicationTests {
	@Autowired
	private NoticeRepository repo;
	private static final Integer FIRST_MESSAGE_ID = 1;
	private static final Integer SECOND_MESSAGE_ID = 2;
	private static String EDITTED_CONTENT = "foo";
	@Test
	@WithMockUser(username = "admin")
	public void givenUserManager_whenFindAllMessage_thenReturnFirstMessage() {
		List<Notice> details = repo.findAllBy(null, null);
		 
	    assertNotNull(details);
	    assertEquals(1, details.size());
	    assertEquals(FIRST_MESSAGE_ID, details.get(0).getId());
	}
	
	@Test
	@WithMockUser(roles = {"EDITOR"})
	public void 
	  givenRoleEditor_whenFindAllMessage_thenReturn3Message(){
	    List<Notice> details = repo.findAllBy(null, null);
	    
	    assertNotNull(details);
	    assertEquals(3,details.size());
	}
	
	@Test
	@WithMockUser(username = "manager")
	public void 
	  givenUserManager_whenFind1stMessageByIdAndUpdateItsContent_thenOK(){
	    Notice firstMessage = repo.findById(FIRST_MESSAGE_ID);
	    assertNotNull(firstMessage);
	    assertEquals(FIRST_MESSAGE_ID,firstMessage.getId());
	        
	    firstMessage.setContent(EDITTED_CONTENT);
	    repo.insert(firstMessage);
	        
	    Notice editedFirstMessage = repo.findById(FIRST_MESSAGE_ID);
	 
	    assertNotNull(editedFirstMessage);
	    assertEquals(FIRST_MESSAGE_ID,editedFirstMessage.getId());
	    assertEquals(EDITTED_CONTENT,editedFirstMessage.getContent());
	}
	
	@Test(expected = AccessDeniedException.class)
	@WithMockUser(roles = {"EDITOR"})
	public void 
	  givenRoleEditor_whenFind1stMessageByIdAndUpdateContent_thenFail(){
	    Notice firstMessage = repo.findById(FIRST_MESSAGE_ID);
	 
	    assertNotNull(firstMessage);
	    assertEquals(FIRST_MESSAGE_ID,firstMessage.getId());
	 
	    firstMessage.setContent(EDITTED_CONTENT);
	    repo.insert(firstMessage);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void givenUsernameHr_whenFindMessageById2_thenOK(){
	    Notice secondMessage = repo.findById(SECOND_MESSAGE_ID);
	    assertNotNull(secondMessage);
	    assertEquals(SECOND_MESSAGE_ID,secondMessage.getId());
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "hr")
	public void givenUsernameHr_whenUpdateMessageWithId2_thenFail(){
	    Notice secondMessage = new Notice();
	    secondMessage.setId(SECOND_MESSAGE_ID);
	    secondMessage.setContent(EDITTED_CONTENT);
	    repo.insert(secondMessage);
	}
}
