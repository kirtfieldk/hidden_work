package com.worklog.workload.repo;

import com.worklog.workload.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService implements RepoInterface {
    private final ProjectRepository projectRepository;
    public  RepoService(ProjectRepository projectRepository){this.projectRepository = projectRepository;}
    @Override
    public List<Repo> fetchAllRepos() {
        return projectRepository.fetchAllRepos();
    }

    @Override
    public Repo fetchRepoById(int id) {
        return projectRepository.fetchRepoById(id);
    }

    @Override
    public Repo fetchRepoByName(String name) {
        return projectRepository.fetchRepoByName(name);
    }
}
