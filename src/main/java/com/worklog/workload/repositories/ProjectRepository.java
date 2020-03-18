package com.worklog.workload.repositories;

import com.worklog.workload.projects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProjectRepository(JdbcTemplate jdbc){
        this.jdbcTemplate = jdbc;
    }
    public List<Project> getAllProjects(){
        String stm = "SELECT * FROM Project";
        return jdbcTemplate.query(stm, mapProjectFromDb());
    }
    private RowMapper<Project> mapProjectFromDb(){
        return(resultSet, i) ->{
            Long id = Long.parseLong(resultSet.getString("project_id"));
            String repo_name = resultSet.getString("repository");
            String description = resultSet.getString("description");
            return new Project(id, repo_name, description);
        };
    }
}
