package com.worklogger_differences.worklogger.routes;

import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.services.DbService;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{fileOne}/{fileTwo}")
    public ReturnMessage differenceBetweenTwoFiles(@PathVariable("fileOne") FileContentTable fileOne,
                                                   @PathVariable("fileTwo") FileContentTable fileTwo){
        return dbService.displayDifferenceBetweenFiles(fileOne, fileTwo);
    }
}
