package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.spec.PSource;
import java.util.List;

@RestController
@RequestMapping("api/v1/difference")
public class DifferenceController {
    @Autowired
    private final DbService dbService;
    public DifferenceController(DbService dbService){
        this.dbService=dbService;
    }
    @GetMapping
    public List<DifferenceTable> fetchAllDifference(){
        return dbService.fetchAllDifferences();
    }

    //Create multiple difference object between the two files
    //and displays an arrayList through ResponseMessage
    //Also it saves the difference into the db
    @PostMapping()
    public ReturnMessage differenceBetweenTwoFiles(@RequestBody List<FileContentTable> files){
        try {
            return dbService.displayDifferenceBetweenFiles(files.get(0), files.get(1));
        }catch (CompareDifferentFilesException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Files are not historically the same", e);
        }
    }
    @GetMapping("/id/{fileOneId}/{fileTwoId}")
    public ResponseEntity<ReturnMessage> differenceBetweenTwoFiles(@PathVariable("fileOneId") long fileOneId,
                                                   @PathVariable("fileTwoId") long fileTwoId){
        try {
                FileContentTable source;
                FileContentTable dest;
                FileContentTable f1 = dbService.fetchFileContentById(fileOneId);
                FileContentTable f2 = dbService.fetchFileContentById(fileTwoId);
                if(f1.getContentId() < f2.getContentId()){
                     source = f1;
                     dest = f2;
                }else{
                     source =  f2;
                     dest = f1;
                }
                dbService.findDifferenceBetweenTwoFilesRecursive(dest.getContent().split("\n"),
                        f1.getFileId(),
                        source.getContent().split("\n"),
                        source.getContentId(),
                        dest.getContentId() ,0);

                return new ResponseEntity<ReturnMessage>(new ReturnMessage("Success adding "+
                        "differences", 202), HttpStatus.OK);
        }catch ( FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Files are not historically the same", e);
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<DifferenceTable> fetchDiffById(@PathVariable("id") long id){
        try{
            return dbService.fetchDifById(id);
        }catch (FileNotFoundInDbException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No File with ID: " + id, e);
        }
    }


}
