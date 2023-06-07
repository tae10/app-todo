package org.edupoll.repository;

import org.apache.ibatis.session.SqlSession;
import org.edupoll.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements AuthInterface {

	@Autowired
	SqlSession sqlSession;

	@Override
	public int join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("pass : " + user.getPassword());
		return sqlSession.insert("users.join", user);
	}

	@Override
	public User select(String id) {
		return sqlSession.selectOne("users.findById", id);
	}

}
