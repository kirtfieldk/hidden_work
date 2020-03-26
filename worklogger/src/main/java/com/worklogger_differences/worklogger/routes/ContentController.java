package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.exception.MissingParamsException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    public FileContentTable fetchContentWithId(@PathVariable("id") long id){
        try {
            return dbService.fetchFileContentById(id);
        }catch (FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File Not Found", e);
        }
        }

    @PostMapping
    public ReturnMessage addContentToDb(@RequestBody FileContentTable file) {
        try {
            return dbService.saveFileContentToDb(file);
        }catch (MissingParamsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Must be an instance of Content", e);
        }
        }
    @PostMapping("/list")
    public ReturnMessage addContentToDbArray(@Valid @RequestBody FileContentTable[] file){
        try {
            for (FileContentTable x : file) {
                dbService.saveFileContentToDb(x);
            }
        }catch (MissingParamsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Must be an instance of Content", e);
        }
        return null;
    }
}
