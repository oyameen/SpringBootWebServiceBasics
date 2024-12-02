package com.oyameen.SpringBootWebServiceBasics.service;


import com.oyameen.SpringBootWebServiceBasics.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);

    Project updateProject(Project project);

    List<Project> getProjects();

    Project getProject(Long id);

    void deleteProject(Long id);
}
