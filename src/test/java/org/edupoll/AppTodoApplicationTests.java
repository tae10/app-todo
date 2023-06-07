package org.edupoll;

import org.edupoll.model.Todo;
import org.edupoll.repository.TodoRepository;
import org.edupoll.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTodoApplicationTests {

	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	AuthService authService;
	
	@Test
	void contextLoads() {
//		todoRepository.create(new Todo("z1", "master", null, null, null));
//		todoRepository.create(new Todo("z2", "mas", null, null, null));
//		todoRepository.create(new Todo("z3", "ter", null, null, null));
//		Todo found = todoRepository.findById("z2");
//		System.out.println(found);
		todoRepository.deleteById("1c965954"); 
		
		
//	System.out.println(authService.isValidate("user", "1q2w3e4r"));	
//	System.out.println(authService.isValidate("user", "1qe4r"));	
//	System.out.println(authService.isValidate("test", "1q2w3e4r"));	
		

	}

}
