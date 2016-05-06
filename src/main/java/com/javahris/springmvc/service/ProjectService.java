package com.javahris.springmvc.service;

import com.javahris.springmvc.model.Project;

public interface ProjectService {
	Project findById(int id);
	Project findByTeamLeadId(int id);
}
