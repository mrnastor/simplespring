package com.javahris.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javahris.springmvc.dao.ProjectDao;
import com.javahris.springmvc.model.Project;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao dao;

	public Project findById(int id) {
		return dao.findById(id);
	}

	public Project findByTeamLeadId(int id) {
		return dao.findByTeamLeadId(id);
	}
}
