package com.worklogger_differences.worklogger.routes;
import com.worklogger_differences.worklogger.exception.FileAlreadyInDb;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/files/")
public class FileController {
    @Autowired
    private final DbService dbService;
    public FileController(DbService dbService){
        this.dbService=dbService;
    }
//    @GetMapping("{fileIdOne}/{fileIdTwo}")
//    public List<String> getDif(@PathVariable("fileIdOne") int fileIdOne,
//                               @PathVariable("fileIdTwo") int fileIdTwo){
//        try {
//            FileContentTable fileOne = dbService.fetchFileContentById(fileIdOne);
//            FileContentTable fileTwo = dbService.fetchFileContentById(fileIdTwo);
//            return dbService.findDifferenceBetweenTwoFilesRecursive(fileOne, fileTwo);
//        }catch(FileNotFoundInDbException | CompareDifferentFilesException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File Id not in db", e);
//        }
//
//    }
    @GetMapping("id/{id}")
    public ResponseEntity<FilesTable> fetchFileById(@PathVariable("id") String id){
        try{
            return dbService.fetchFileById(id);
        }catch (FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File Id not in Database: " + id, e );
        }
    }
/*
    Adds file to table
 */
    @PostMapping
    public ResponseEntity<ReturnMessage> addFileToDb(@RequestBody FilesTable file){
        try {
            return dbService.saveFileToDb(file);
        }catch (FileAlreadyInDb e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not a valid File object", e);
        }
        }
    @PostMapping("/list")
    public ResponseEntity<ReturnMessage> addListOfFilesToDb(@RequestBody List<FilesTable> files){
        try {
            return dbService.saveManyFilesToDb(files);
        }catch (FileAlreadyInDb e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Some Files already in DD", e);
        }

        }

    /*
        Fetch All files
     */
    @GetMapping
    public List<FilesTable> fetchAllFiles(){
        return dbService.fetchAllFiles();
    }




}
