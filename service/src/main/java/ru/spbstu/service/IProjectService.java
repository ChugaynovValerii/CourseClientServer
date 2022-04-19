package ru.spbstu.service;


import ru.spbstu.entity.Project;

import java.util.List;

public interface IProjectService {
    List<Project> listProjects();
    Project getProject(long id);
    Project getProject(String name);
    Project addProject(Project project);
    void deleteProject(long id);
}
