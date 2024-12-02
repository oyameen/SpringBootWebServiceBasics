package com.oyameen.SpringBootWebServiceBasics.controller;

import com.oyameen.SpringBootWebServiceBasics.model.Project;
import com.oyameen.SpringBootWebServiceBasics.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/project")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return ResponseEntity.status(201).body(projectService.saveProject(project));
    }

    @PutMapping(value = "/project")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        Project projectResult = projectService.updateProject(project);
        if (projectResult != null)
            return ResponseEntity.ok(projectResult);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/project")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @GetMapping(value = "/project/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/project/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
