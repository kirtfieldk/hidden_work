package com.worklog.workload.repo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="repositories")
public class Repo {
    @Column(name = "repository_id")
    @Id
    private final int repositoryId;
    @Column(name="project_id")
    @NotNull
    private final int projectId;
    @Column(name="repository")
    @NotBlank
    private final String repository;
    @Column(name="branch")
    @NotBlank
    private final String branch;
    @Column(name="clone_url")
    @NotBlank
    private final String cloneUrl;


    public Repo(@JsonProperty("repository_id") int repositoryId,
                @JsonProperty("project_id") int projectId,
                @JsonProperty("branch") String branch,
                @JsonProperty("repository") String repository,
                @JsonProperty("clone_url") String cloneUrl){
        this.repositoryId=repositoryId;
        this.repository=repository;
        this.branch=branch;
        this.cloneUrl=cloneUrl;
        this.projectId=projectId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getRepository() {
        return repository;
    }

    public String getBranch() {
        return branch;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }
}
