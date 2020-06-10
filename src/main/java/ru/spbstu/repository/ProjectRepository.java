package ru.spbstu.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);
}
