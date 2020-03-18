package com.worklog.workload.projects;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="project")
public class Project {
    @Id
    private long id;
    @NotBlank
    private String repo_name;
    @NotBlank
    private String description;

    public Project(@JsonProperty("id") long id,
                   @JsonProperty("repository") String repo_name,
                   @JsonProperty("description") String description){
        this.id = id;
        this.repo_name = repo_name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getRepo_name() {
        return repo_name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final Project other = (Project) obj;
        if(!Objects.equals(this.repo_name, other.repo_name)) return false;
        if(!Objects.equals(this.description, other.description)) return false;
        return Objects.equals(this.id, other.id);
    }
    @Override
    public String toString(){
        return "Project" +repo_name;
    }

}
