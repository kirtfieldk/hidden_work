package com.worklog.workload.commits;

import java.util.List;

public interface CommitsInterface {
    public List<Commits> fetchAllCommits();
    public Commits fetchCommitById();
}
