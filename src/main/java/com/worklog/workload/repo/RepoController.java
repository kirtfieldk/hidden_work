package com.worklog.workload.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/repositories/")
public class RepoController {
    private final RepoService repoService;
    @Autowired
    public RepoController(RepoService repoService){this.repoService = repoService;}

    @GetMapping
    public List<Repo> fetchAllRepos(){
        return repoService.fetchAllRepos();
    }
    @GetMapping(path = "id/{id}")
    public Repo fetchRepoById(@PathVariable("id")int id){
        return repoService.fetchRepoById(id);
    }
    @GetMapping(path="name/{name}")
    public Repo fetchRepoByName(@PathVariable("name")String name){
        return repoService.fetchRepoByName(name);
    }
}
