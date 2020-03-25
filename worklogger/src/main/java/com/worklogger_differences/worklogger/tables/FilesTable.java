package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*__________________________________________
    __id__|__filename__|__repo__|__project__
 */

@Entity
@Table(name="files")
public class FilesTable {
    @Id
    @Column(name="file_id")
    private final String id;
    @Column(name="file_name")
    private final String fileName;
    @Column(name="repository")
    private final String repository;
    @Column(name="project")
    private final String project;

    public FilesTable(){
        super();
        this.id=null;
        this.fileName=null;
        this.repository=null;
        this.project=null;
    }
    public FilesTable(@JsonProperty("id") String id,
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
