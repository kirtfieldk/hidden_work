package com.worklog.workload.repo;

import java.util.List;

public interface RepoInterface {
    public List<Repo> fetchAllRepos();
    public Repo fetchRepoById(int id);
    public Repo fetchRepoByName(String name);
}
