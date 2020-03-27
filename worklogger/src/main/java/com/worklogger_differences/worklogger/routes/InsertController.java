package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.InsertTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/insertion")
public class InsertController {
    @Autowired
    private final DbService dbService;
    public InsertController(DbService dbService){
        this.dbService = dbService;
    }
    @GetMapping
    public List<InsertTable> fetchAllInserts(){
        return dbService.fetchAllInsert();
    }
    @GetMapping("/{id}")
    public ResponseEntity<InsertTable> fetchInsertById(@PathVariable("id") long id){
        try{
        return dbService.fetchInsertById(id);
    }catch(FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File Not found wit ID: "+id);
        }
    }

    @GetMapping("/file/content/{fileOneId}/{fileTwoId}")
    public List<InsertTable> fetchAllInsertBetweenFiles(@PathVariable("fileOneId") long idOne,
                                                        @PathVariable("fileTwoId") long idTwo){
        long recent = Math.max(idOne,idTwo);
        long older = Math.min(idOne, idTwo);
        return dbService.fetchAllInsertForFile(recent, older);
    }






}
