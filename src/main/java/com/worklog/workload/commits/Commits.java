package com.worklog.workload.commits;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="commits")
public class Commits {
    @Column(name="commit_id")
    @Id
    private final String commitId;
    @Column(name="committer")
    @NotBlank
    private final String committer;
    @Column(name="message")
    @NotBlank
    private final String message;
    @Column(name="commited_at")
    @NotBlank
    private final String committedAt;
    @Column(name="repository_id")
    @NotNull
    private final int repositoryId;

    public Commits(@JsonProperty("commit_id") String commitId,
                  @JsonProperty("committer") String committer,
                  @JsonProperty("message") String message,
                  @JsonProperty("committed_at") String committedAt,
                  @JsonProperty("repository_id") int repoId){
        this.commitId=commitId;
        this.committedAt=committedAt;
        this.committer=committer;
        this.message=message;
        this.repositoryId=repoId;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getCommitter() {
        return committer;
    }

    public String getMessage() {
        return message;
    }

    public String getCommittedAt() {
        return committedAt;
    }

    public int getRepositoryId() {
        return repositoryId;
    }
}
