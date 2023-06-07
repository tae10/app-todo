package org.edupoll.service;

import java.util.List;
import java.util.UUID;

import org.edupoll.model.Quest;
import org.edupoll.model.Todo;
import org.edupoll.repository.QuestRepository;
import org.edupoll.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestService {

	@Autowired
	QuestRepository questRepository;
	@Autowired
	TodoRepository todorepository;

	public List<Quest> getThisWeekQuest() {
		List<Quest> quest = questRepository.findAll();

		return quest;
	}

	@Transactional
	public boolean changeTodo(int id, String logonId) {
		Quest quest = questRepository.findById(id);
		String uuid = UUID.randomUUID().toString().split("-")[0];
		List<Todo> todos = todorepository.findByOwner(logonId);
		if (todos.stream().filter(t -> {
			return t.getDescription().equals(quest.getDescription());
		}).toList().isEmpty()) {

			Todo todo = new Todo();
			todo.setDescription(quest.getDescription());
			todo.setOwner(logonId);
			todo.setTargetDate(quest.getEndDate());
			todo.setId(uuid);

			todorepository.create(todo);
			quest.setJoinCnt(quest.getJoinCnt() + 1);
			questRepository.update(quest);

			return true;
		} else {
			return false;

		}

	}
}
