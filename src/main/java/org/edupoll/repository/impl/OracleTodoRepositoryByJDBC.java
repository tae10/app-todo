package org.edupoll.repository.impl;

import java.util.List;

import org.edupoll.model.Todo;
import org.edupoll.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OracleTodoRepositoryByJDBC implements TodoRepository {
	@Autowired
	JdbcTemplate template;

	@Override
	public void create(Todo todo) {
		template.update("INSERT INTO TODOS VALUES(?,?,?,?,?)", todo.getId(), todo.getOwner(), todo.getDescription(),
				todo.getTargetDate(), todo.getDone());
	}

	@Override
	public Todo findById(String id) {
		List<Todo> list = template.query("SELECT * FROM TODOS WHERE ID=?", (rs, rowNum) -> {
			Todo todo = new Todo();
			todo.setId(rs.getString("id"));
			todo.setDescription(rs.getString("description"));
			todo.setDone(rs.getString("done"));
			todo.setOwner(rs.getString("owner"));
			todo.setTargetDate(rs.getDate("target_date"));
			return todo;
		}, id);

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<Todo> findByOwner(String owner) {
		return template.query("SELECT * FROM TODOS WHERE OWNER=?", (rs, rowNum) -> {
			Todo todo = new Todo();
			todo.setId(rs.getString("id"));
			todo.setDescription(rs.getString("description"));
			todo.setDone(rs.getString("done"));
			todo.setOwner(rs.getString("owner"));
			todo.setTargetDate(rs.getDate("target_date"));
			return todo;
		}, owner);
	}

	@Override
	public void deleteById(String id) {
		template.update("DELETE FROM TODOS WHERE ID=?", id);
	}

	@Override
	public void update(Todo todo) {
		// TODO Auto-generated method stub
		
	}
}


