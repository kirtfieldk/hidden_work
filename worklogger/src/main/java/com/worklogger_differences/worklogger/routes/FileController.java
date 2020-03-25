package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/files/")
public class FileController {
    @Autowired
    private final DbService dbService;
    public FileController(DbService dbService){
        this.dbService= dbService;
    }
    @GetMapping("{fileIdOne}/{fileIdTwo}")
    public List<String> getDif(@PathVariable("fileIdOne") int fileIdOne,
                               @PathVariable("fileIdTwo") int fileIdTwo){
        FileContentTable fileOne = dbService.fetchFileContentById(fileIdOne);
        FileContentTable fileTwo = dbService.fetchFileContentById(fileIdTwo);
        return dbService.findDifferenceBetweenTwoFiles(fileOne, fileTwo);
    }
}
