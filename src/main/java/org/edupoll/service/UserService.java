package org.edupoll.service;

import org.edupoll.model.User;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public boolean joinService(String id, String password) {
		User alreadyUser = userRepository.select(id);
		if (alreadyUser != null) {
			return false;
		} else {

			User user = new User();
			user.setId(id);
			user.setPassword(password);
			if (user.getId().equals(alreadyUser.getId())) {

				return false;
			}
			int r = userRepository.join(user);
			if (r == 1) {
				return true;
			} else {
				return false;
			}
		}

	}

}
