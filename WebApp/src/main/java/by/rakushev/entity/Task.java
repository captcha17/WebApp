package by.rakushev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Task extends AbstractEntity {

	@Column(name = "nameTask")
	private String nameTask;

	@Column(name = "project_id")
	private Long project_id;

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	@Override
	public boolean entityEquals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int entityHashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
