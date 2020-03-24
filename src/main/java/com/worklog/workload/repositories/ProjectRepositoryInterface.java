package com.worklog.workload.repositories;

import com.worklog.workload.commits.Commits;
import com.worklog.workload.files.Files;
import com.worklog.workload.projects.Project;
import com.worklog.workload.repo.Repo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ProjectRepositoryInterface {
    //Project DB methods
    public List<Project> getAllProjects();

    //Repo DB methods
    public List<Repo> fetchAllRepos();
    public Repo fetchRepoById(int id);
    public Repo fetchRepoByName(String name);
    //File DD methods
    public List<Files> fetchFilesInRepoName(String name);
    public Files fetchFileByName(String name);
    public Files fetchFileById(String id);
    public List<Files> fetchFilesInProjectByName(String name);
    public List<Files> fetchFilesInProjectById(int id);
    public List<Files> fetchAllFiles();
    //Commits DB methods
    public List<Commits> fetchAllCommits();
    public Commits fetchCommitById(String id);
    /*
Row Mappers map rows of the fetched db into a
List in Jave
*/
    public default RowMapper<Project> mapProjectFromDb(){
        return(resultSet, i) ->{
            Long id = Long.parseLong(resultSet.getString("project_id"));
            String repo_name = resultSet.getString("project");
            String description = resultSet.getString("description");
            return new Project(id, repo_name, description);
        };
    }
    public default RowMapper<Repo> mapRepoFromDb(){
        return(resultSet, i) -> {
            int repositoryId = Integer.parseInt(resultSet.getString("repository_id"));
            String repository = resultSet.getString("repository");
            int projectId = Integer.parseInt(resultSet.getString("project_id"));
            String branch = resultSet.getString("branches");
            String url = resultSet.getString("clone_url");
            return new Repo(repositoryId, projectId, branch, repository, url);
        };
    }
    public default RowMapper<Files> mapFilesFromDb(){
        return (resultSet, i) -> {
         String fileId=resultSet.getString("file_id");
         String fileName = resultSet.getString("file_name");
         String repo = resultSet.getString("repository");
         String branch = resultSet.getString("branch");
         String fileContent = resultSet.getString("file_content");
         int projectId = Integer.parseInt(resultSet.getString("project_id"));
         int repo_id = Integer.parseInt(resultSet.getString("repository_id"));
        return new Files(fileId, fileName,repo,branch,fileContent,projectId,repo_id);
        };
    }
    public default RowMapper<Commits> mapCommitsFromDb(){
        return (resultSet, i) -> {
            String commitId = resultSet.getString("commit_id");
            String message = resultSet.getString("message");
            String committer = resultSet.getString("committer");
            String commitedAt = resultSet.getString("commited_at");
            int repoId = Integer.parseInt(resultSet.getString("repository_id"));
            return new Commits(commitId, committer,message,commitedAt,repoId);
        };
    }

}
