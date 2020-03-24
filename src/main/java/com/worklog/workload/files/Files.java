package com.worklog.workload.files;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="files")
public class Files {

    @Column(name="file_id")
    @Id
    private final String fileId;
    @Column(name="file_name")
    @NotBlank
    private final String fileName;
    @Column(name="repository")
    @NotBlank
    private final String repository;
    @Column(name="branch")
    @NotBlank
    private final String branch;
    @Column(name="file_content")
    @NotBlank
    private final String fileContent;
    @Column(name="project_id")
    @NotNull
    private final int projectId;
    @Column(name="repository_id")
    @NotNull
    private final int repositoryId;
    public Files(@JsonProperty("file_id") String fileId,
                 @JsonProperty("file_name") String fileName,
                 @JsonProperty("repository") String repository,
                 @JsonProperty("branch") String branch,
                 @JsonProperty("file_content") String fileContent,
                 @JsonProperty("project_id") int projectId,
                 @JsonProperty("repository_id") int repositoryId
                 ){
        this.fileId=fileId;
        this.branch=branch;
        this.fileContent=fileContent;
        this.repository=repository;
        this.projectId=projectId;
        this.repositoryId=repositoryId;
        this.fileName=fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRepository() {
        return repository;
    }

    public String getBranch() {
        return branch;
    }

    public String getFileContent() {
        return fileContent;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }


}
