package com.oyameen.SpringBootWebServiceBasics.service.impl;

import com.oyameen.SpringBootWebServiceBasics.exception.EmployeeManagementException;
import com.oyameen.SpringBootWebServiceBasics.model.Project;
import com.oyameen.SpringBootWebServiceBasics.repository.ProjectRepository;
import com.oyameen.SpringBootWebServiceBasics.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Project projectResult = projectRepository.findById(project.getId()).orElse(null);
        if (projectResult != null) {
            projectResult.setName(project.getName());
            projectResult.setStartDate(project.getStartDate());
            projectResult.setEndDate(project.getEndDate());
            return projectRepository.save(projectResult);
        }
        throw new EmployeeManagementException("Cannot update this Project, because it doesn't exist.");
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProject(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new EmployeeManagementException("Cannot delete this Project, because it doesn't exist.");
        }
        projectRepository.deleteById(id);
    }
}
