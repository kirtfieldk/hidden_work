package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    @GetMapping("/{fileOne}/{fileTwo}")
    public ReturnMessage differenceBetweenTwoFiles(@PathVariable("fileOne") FileContentTable fileOne,
                                                   @PathVariable("fileTwo") FileContentTable fileTwo){
        try {
            if (fileOne.getFileId().equals(fileTwo.getFileId()))
                return dbService.displayDifferenceBetweenFiles(fileOne, fileTwo);
            return new ReturnMessage("Files are not the same", 404);
        }catch (CompareDifferentFilesException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Files are not historically the same", e);
        }
        }



}
