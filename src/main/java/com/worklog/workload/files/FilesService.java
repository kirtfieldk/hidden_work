package com.worklog.workload.files;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService implements FilesInterface {

    @Override
    public List<Files> fetchFilesInRepoName(String name) {
        String stm ="SELECT * FROM files where repository = '"+name+"';";
        //List<Files> files =
        return null;
    }

    @Override
    public Files fetchFileByName(String name) {
        return null;
    }

    @Override
    public Files fetchFileById(String id) {
        return null;
    }

    @Override
    public List<Files> fetchFilesInProject(String name) {
        return null;
    }
}
