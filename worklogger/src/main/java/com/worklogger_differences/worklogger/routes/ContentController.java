package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/content")
public class ContentController {
    @Autowired
    private final DbService dbService;
    public ContentController(DbService dbService){
        this.dbService=dbService;
    }
    @GetMapping
    public List<FileContentTable> getAllContent(){
        return dbService.fetchAllFileContent();
    }
    @GetMapping("/{id}")
    public FileContentTable fetchContentWithId(@PathVariable("id") int id){
        return dbService.fetchFileContentById(id);
    }
    @PostMapping
    public ReturnMessage addContentToDb(@RequestBody FileContentTable file){
        return dbService.saveFileContentToDb(file);
    }
}
