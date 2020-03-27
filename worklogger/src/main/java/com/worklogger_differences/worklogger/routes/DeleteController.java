package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.DeleteTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/delete")
public class DeleteController {
    @Autowired
    private final DbService dbService;
    public DeleteController(DbService dbService){
        this.dbService=dbService;
    }


    @GetMapping
    public List<DeleteTable> fetchAllDelete(){
        return dbService.fetchAllDelete();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<DeleteTable> fetchDeleteById(@PathVariable("id") long id){
        try{
            return dbService.fetchDeleteById(id);
        }catch(FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File Not found with ID: " + id, e);
        }
    }
    @GetMapping("/file/content/old/{id}")
    public List<DeleteTable> fetchAllDeleteForFileContentOld(@PathVariable long id){
      return dbService.fetchAllDeleteForFileContentOld(id);
    }
    @GetMapping("/file/content/new/{id}")
    public List<DeleteTable> fetchAllDeleteForFileContentNew(@PathVariable long id){
        return dbService.fetchAllDeleteForFileContentNew(id);
    }
}
