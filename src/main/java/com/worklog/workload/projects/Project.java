package com.worklog.workload.projects;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="project")
public class Project {
    @Column(name="project_id")
    @Id
    private final long id;
    @Column(name="project")
    @NotBlank
    private final String project;
    @Column(name="description")
    @NotBlank
    private final String description;

    public Project(@JsonProperty("id") long id,
                   @JsonProperty("project") String project,
                   @JsonProperty("description") String description){
        this.id = id;
        this.project = project;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final Project other = (Project) obj;
        if(!Objects.equals(this.project, other.project)) return false;
        if(!Objects.equals(this.description, other.description)) return false;
        return Objects.equals(this.id, other.id);
    }
    @Override
    public String toString(){
        return "Project" +project;
    }

}
