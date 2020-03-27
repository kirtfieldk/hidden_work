package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
/*__________________________________________
    __id__|__filename__|__repo__|__project__
 */

@Entity
@Table(name="files")
public class FilesTable {
    @Id
    @Column(name="file_id")
    @NotEmpty(message = "Must have a file ID")
    private String id;
    @Column(name="file_name")
    @NotEmpty(message = "Must have a filename")
    private String fileName;
    @Column(name="repository")
    @NotEmpty(message = "Must have a repository")
    private String repository;
    @Column(name="project")
    @NotEmpty(message = "Must have a project")
    private String project;

    public FilesTable(){
        super();
    }
    public FilesTable(@JsonProperty("file_id") String id,
                       @JsonProperty("file_name") String fileName,
                       @JsonProperty("repository") String repository,
                       @JsonProperty("project") String project){
        this.id=id;
        this.fileName=fileName;
        this.repository=repository;
        this.project=project;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRepository() {
        return repository;
    }

    public String getProject() {
        return project;
    }
}
