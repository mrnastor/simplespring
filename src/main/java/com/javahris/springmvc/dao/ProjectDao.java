package com.javahris.springmvc.dao;

import com.javahris.springmvc.model.Project;

public interface ProjectDao {
	Project findById(int id);
	Project findByTeamLeadId(int id);
}
