package com.worklog.workload.files;

import java.util.List;

public interface FilesInterface {
    public List<Files> fetchFilesInRepoName(String name);
    public Files fetchFileByName(String name);
    public Files fetchFileById(String id);
    public List<Files> fetchFilesInProject(String name);

}
