package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.exception.FileAlreadyInDb;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.exception.MissingParamsException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@Repository
public interface DbManipluationInterface {
    /*
        Response functions that call
        the private methods
     */
    List<FileContentTable> fetchAllFileContent();
    List<DifferenceTable> fetchAllDifferences();
    List<FilesTable> fetchAllFiles();
    ResponseEntity<FilesTable> fetchFileById(String id)
            throws FileNotFoundInDbException;
    ResponseEntity<DifferenceTable> fetchDifById(long id)
            throws FileNotFoundInDbException;
    FileContentTable fetchFileContentById(long id)
            throws FileNotFoundInDbException;
    List<String> findDifferenceBetweenTwoFiles(FileContentTable one, FileContentTable two)
            throws CompareDifferentFilesException;
    ReturnMessage displayDifferenceBetweenFiles(FileContentTable one, FileContentTable two)
            throws CompareDifferentFilesException;
    DifferenceTable createDifferenceObject(FileContentTable fileOne, FileContentTable fileTwo, String dif);
    ResponseEntity<ReturnMessage> saveFileToDb(FilesTable file)
            throws FileAlreadyInDb;
    ResponseEntity<ReturnMessage> saveFileContentToDb(FileContentTable fileContent)
            throws FileNotFoundInDbException;
    ResponseEntity<ReturnMessage> saveDiffToDb(DifferenceTable diff);
    ResponseEntity<ReturnMessage> saveManyFilesToDb(List<FilesTable> files)
            throws FileAlreadyInDb;
    ResponseEntity<ReturnMessage> saveManyFileContentToDb(List<FileContentTable> files)
            throws FileNotFoundInDbException;

    Boolean fileInDb(String fileId);
    Boolean fileContentInDb(long id);
/*
    Mapping a row into an
        object
 */
    default RowMapper<FilesTable> mapFilesFromDb(){
        return (resultSet, i) -> {
            String id = resultSet.getString("file_id");
            String fileName = resultSet.getString("file_name");
            String repository = resultSet.getString("repository");
            String project = resultSet.getString("repository");
            return new FilesTable(id, fileName, repository,project);
        };
    }
    default RowMapper<DifferenceTable> mapDifferenceFromDb(){
        return (resultSet, i) -> {
            DifferenceTable temp = new DifferenceTable();
            temp.setId(Long.parseLong(resultSet.getString("difference_id")));
            temp.setFileId(resultSet.getString("file_id"));
            temp.setContentOne(Long.parseLong(resultSet.getString("content_one_id")));
            temp.setContentTwo(Long.parseLong(resultSet.getString("content_two_id")));
            temp.setDifferences(resultSet.getString("differences"));
            return temp;
        };
    }
    default RowMapper<FileContentTable> mapContentFromDb(){
        return (resultSet, i) -> {
            FileContentTable temp = new FileContentTable();
            temp.setContentId(Long.parseLong(resultSet.getString("content_id")));
            temp.setFileId(resultSet.getString("file_id"));
            temp.setContent(resultSet.getString("content"));
            temp.setPushedAt(resultSet.getString("pushed_at"));
            return temp;
        };
    }


}
