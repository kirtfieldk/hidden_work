package com.worklog.workload.files;

import com.worklog.workload.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/files/")
public class FilesController {
    private ProjectRepository projectRepository;
    public FilesController(ProjectRepository projectRepository){this.projectRepository=projectRepository;}
    @GetMapping
    public List<Files> getAllFiles(){
        return projectRepository.fetchAllFiles();
    }
    @GetMapping("project/name/{name}")
    public List<Files> fetchAllFilesInRepoByName(@PathVariable("name") String name){
        return projectRepository.fetchFilesInProjectByName(name);
    }
    @GetMapping("project/id/{id}")
    public List<Files> fetchAllFilesInRepoByName(@PathVariable("id") int id){
        return projectRepository.fetchFilesInProjectById(id);
    }
    @GetMapping("name/{name}")
    public Files getFileByName(@PathVariable("name") String name){
        return projectRepository.fetchFileByName(name);
    }
    @GetMapping("id/{id}")
    public Files getFileById(@PathVariable("id") String id){
        return projectRepository.fetchFileById(id);
    }

}
