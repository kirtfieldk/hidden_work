package com.worklog.workload.projects;

import com.worklog.workload.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService implements ProjectInterface {
    @Autowired
    private ProjectRepository projectRepository;
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    @Override
    public List<Project> getAllProjects(){
        return  projectRepository.getAllProjects();
    }

}
