package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ReturnMessage> addContentToDb(@RequestBody FileContentTable file) {
        try {
            return dbService.saveFileContentToDb(file);
        }catch (FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File not found with file ID: " + file.getFileId(), e);
        }
        }

    @PostMapping("/list")
    public ResponseEntity<ReturnMessage> addContentToDb(@Valid @RequestBody List<FileContentTable> file){
        try {
            return dbService.saveManyFileContentToDb(file);

        }catch (FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some Files are not in DB", e);
        }
    }
}
