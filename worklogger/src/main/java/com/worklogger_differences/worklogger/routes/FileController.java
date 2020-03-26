package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/files/")
public class FileController {
    @Autowired
    private final DbService dbService;
    public FileController(DbService dbService){
        this.dbService=dbService;
    }
    @GetMapping("{fileIdOne}/{fileIdTwo}")
    public List<String> getDif(@PathVariable("fileIdOne") int fileIdOne,
                               @PathVariable("fileIdTwo") int fileIdTwo){
        FileContentTable fileOne = dbService.fetchFileContentById(fileIdOne);
        FileContentTable fileTwo = dbService.fetchFileContentById(fileIdTwo);
        return dbService.findDifferenceBetweenTwoFiles(fileOne, fileTwo);
    }
/*
    Adds file to table
 */
    @PostMapping
    public ReturnMessage addFileToDb(@RequestBody FilesTable file){
        if(!dbService.fileInDb(file.getId()))
            return dbService.saveFileToDb(file);
        return new ReturnMessage("File already in db", 404);
    }
    /*
        Fetch All files
     */
    @GetMapping
    public List<FilesTable> fetchAllFiles(){
        return dbService.fetchAllFiles();
    }




}
