package ru.spbstu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.spbstu.entity.Project;
import ru.spbstu.exception.ProjectNotFoundException;
import ru.spbstu.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    
    private final ProjectRepository projectRepository;
    
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    @Override
    public List<Project> listProjects() {
        return (List<Project>) projectRepository.findAll();
    }
    
    @Override
    public Project getProject(long id) {
        Optional<Project> optionalApp = projectRepository.findById(id);
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new ProjectNotFoundException("Project not found");
        }
    }
    
    @Override
    public Project getProject(String name) {
        Optional<Project> optionalProject = Optional.ofNullable(projectRepository.findByName(name));
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new ProjectNotFoundException("Project not found");
        }
    }
    
    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
    
    @Override
    public void deleteProject(long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ProjectNotFoundException("There is no project with this ID");
        }
    }
}
