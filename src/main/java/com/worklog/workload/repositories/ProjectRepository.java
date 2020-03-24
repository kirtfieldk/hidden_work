package com.worklog.workload.repositories;

import com.worklog.workload.commits.Commits;
import com.worklog.workload.files.Files;
import com.worklog.workload.projects.Project;
import com.worklog.workload.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository implements ProjectRepositoryInterface{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProjectRepository(JdbcTemplate jdbc){
        this.jdbcTemplate = jdbc;
    }
    public List<Project> getAllProjects(){
        String stm = "SELECT * FROM Project;";
        return jdbcTemplate.query(stm, mapProjectFromDb());
    }

    @Override
    public List<Repo> fetchAllRepos() {
        String stm = "SELECT * FROM repositories;";
        return jdbcTemplate.query(stm, mapRepoFromDb());
    }

    @Override
    public Repo fetchRepoById(int id) {
        String stm = "SELECT * FROM repositories WHERE repository_id=" +id+";";
        List<Repo> repoWithId=jdbcTemplate.query(stm, mapRepoFromDb());
        if (!repoWithId.isEmpty())
            return jdbcTemplate.query(stm, mapRepoFromDb()).get(0);
        return null;

    }

    @Override
    public Repo fetchRepoByName(String name) {
        String stm = "SELECT * FROM repositories where repository= '"+name+"';";
        List<Repo> repoWithId=jdbcTemplate.query(stm, mapRepoFromDb());
        if (!repoWithId.isEmpty())
            return jdbcTemplate.query(stm, mapRepoFromDb()).get(0);
        return null;
    }

    @Override
    public List<Files> fetchFilesInRepoName(String name) {
        String stm ="SELECT * FROM files where repository = '"+name+"';";
        List<Files> files = jdbcTemplate.query(stm, mapFilesFromDb());
        if (!files.isEmpty()) return files;
        return null;
    }

    @Override
    public Files fetchFileByName(String name) {
        String stm ="SELECT * FROM files where file_name = '"+name+"';";
        List<Files> files = jdbcTemplate.query(stm, mapFilesFromDb());
        if (!files.isEmpty()) return files.get(0);
        return null;
    }

    @Override
    public Files fetchFileById(String id) {
        String stm ="SELECT * FROM files where file_id = '"+id+"';";
        List<Files> files = jdbcTemplate.query(stm, mapFilesFromDb());
        if (!files.isEmpty()) return files.get(0);
        return null;
    }
//TODO Fix up this method
    @Override
    public List<Files> fetchFilesInProjectByName(String name) {
        String stm ="SELECT * FROM files join project on project.project='"+name+"';";
        List<Files> files = jdbcTemplate.query(stm, mapFilesFromDb());
        if (!files.isEmpty()) return files;
        return null;
    }
    @Override
    public List<Files> fetchFilesInProjectById(int id) {
        String stm ="SELECT * FROM files where project_id="+id+";";
        List<Files> files = jdbcTemplate.query(stm, mapFilesFromDb());
        if (!files.isEmpty()) return files;
        return null;
    }
    @Override
    public List<Files> fetchAllFiles(){
        String stm = "SELECT * FROM files;";
        return jdbcTemplate.query(stm, mapFilesFromDb());
    }

    @Override
    public List<Commits> fetchAllCommits() {
        String stm = "SELECT * FROM commits;";
        return jdbcTemplate.query(stm,mapCommitsFromDb());
    }

    @Override
    public Commits fetchCommitById(String id) {
        String stm = "SELECT * FROM commits where commit_id='"+id+"';";
        List<Commits>commits = jdbcTemplate.query(stm,mapCommitsFromDb());
        if(!commits.isEmpty()) return commits.get(0);
        return null;
    }

}
