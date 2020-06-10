package ru.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.entity.Project;
import ru.spbstu.exception.ProjectNotFoundException;
import ru.spbstu.service.IDepartmentService;
import ru.spbstu.service.IProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final IProjectService projectService;
    private final IDepartmentService departmentService;
    
    @Autowired
    public ProjectController(IProjectService projectService, IDepartmentService departmentService) {
        this.projectService = projectService;
        this.departmentService = departmentService;
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> list = projectService.listProjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(projectService.getProject(id), HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<Project> getProject(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(projectService.getProject(name), HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }
    
    @PostMapping(value = "/addProject", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        String departmentName = project.getDepartment().getName();
        if (departmentService.getDepartment(departmentName) != null) {
            project.setDepartment(departmentService.getDepartment(departmentName));
        }
        Project savedProject;
        return new ResponseEntity<>(projectService.addProject(project), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") long id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}