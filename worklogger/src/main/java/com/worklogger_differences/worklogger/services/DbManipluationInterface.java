package com.worklogger_differences.worklogger.services;
import com.worklogger_differences.worklogger.exception.FileAlreadyInDb;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.*;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DbManipluationInterface {
    /*
        The Fetch ALl Methods
     */
    List<FileContentTable> fetchAllFileContent();
    List<FilesTable> fetchAllFiles();
    List<DeleteTable> fetchAllDelete();
    List<InsertTable> fetchAllInsert();
    /*
        Fetching entries by keys
     */
    FilesTable fetchFileById(String id)
            throws FileNotFoundInDbException;
    FileContentTable fetchFileContentById(long id)
            throws FileNotFoundInDbException;
    ResponseEntity<InsertTable> fetchInsertById(long id)
            throws FileNotFoundInDbException;
    ResponseEntity<DeleteTable> fetchDeleteById(long id)
            throws FileNotFoundInDbException;
    //Return empty array if none existst//
    List<DeleteTable> fetchAllDeleteForFileContent(long recent, long older);
    List<InsertTable> fetchAllInsertForFile(long recent, long older);
    /*
        Creating differences
     */
    ResponseEntity<String> findDifferenceBetweenTwoFilesRecursive(String[] contentOne, String fileId,
                                                                  String[] contentTwo, long source,
                                                                  long dest, int index);
    /*
        Saving Things to their tables
     */
    ResponseEntity<ReturnMessage> saveFileToDb(FilesTable file)
            throws FileAlreadyInDb;
    ResponseEntity<ReturnMessage> saveFileContentToDb(FileContentTable fileContent)
            throws FileNotFoundInDbException;
    ResponseEntity<ReturnMessage> saveManyFilesToDb(List<FilesTable> files)
            throws FileAlreadyInDb;
    ResponseEntity<ReturnMessage> saveManyFileContentToDb(List<FileContentTable> files)
            throws FileNotFoundInDbException;
    ResponseEntity<DeleteTable> saveDeleteToDb(DeleteTable del);
    ResponseEntity<InsertTable> saveInsertToDb(InsertTable insert);
    /*
        Error Handling
     */
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
    default RowMapper<InsertTable> mapInsertFromDb(){
        return (resultSet, i) -> {
            InsertTable insert = new InsertTable();
            insert.setInsertId(Long.parseLong(resultSet.getString("insertion_id")));
            insert.setFileId(resultSet.getString("file_id"));
            insert.setNewId(Long.parseLong(resultSet.getString("destination")));
            insert.setIndex(Integer.parseInt(resultSet.getString("index")));
            insert.setOldId(Long.parseLong(resultSet.getString("origin")));
            insert.setStringValue(resultSet.getString("string_value"));
            return insert;
        };
    }

    default RowMapper<DeleteTable> mapDeleteFromDb(){
        return (resultSet, i) -> {
            DeleteTable del = new DeleteTable();
            del.setDeleteId(Long.parseLong(resultSet.getString("delete_id")));
            del.setFileId(resultSet.getString("file_id"));
            del.setNewId(Long.parseLong(resultSet.getString("destination")));
            del.setIndex(Integer.parseInt(resultSet.getString("index")));
            del.setOldId(Long.parseLong(resultSet.getString("origin")));
            del.setStringValue(resultSet.getString("string_value"));
            return del;
        };
    }




}
