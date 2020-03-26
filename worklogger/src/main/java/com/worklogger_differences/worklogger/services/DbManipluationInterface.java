package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface DbManipluationInterface {
    List<FileContentTable> fetchAllFileContent();
    List<DifferenceTable> fetchAllDifferences();
    List<FilesTable> fetchAllFiles();
    FileContentTable fetchFileContentById(int id);
    List<String> findDifferenceBetweenTwoFiles(FileContentTable one, FileContentTable two);
    DifferenceTable createDifferenceObject(FileContentTable fileOne, FileContentTable fileTwo, String dif);
    ReturnMessage saveFileToDb(FilesTable file);
    ReturnMessage saveFileContentToDb(FileContentTable fileContent);
    ReturnMessage saveDiffToDb(DifferenceTable dif);
    Boolean fileInDb(String fileId);
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
            int id = Integer.parseInt(resultSet.getString("difference_id"));
            String fileId = resultSet.getString("file_id");
            int idOne = Integer.parseInt(resultSet.getString("content_one_id"));
            int idTwo = Integer.parseInt(resultSet.getString("content_two_id"));
            String difference = resultSet.getString("differences");
            return new DifferenceTable(id, fileId, idOne, idTwo,1,  difference);
        };
    }
    default RowMapper<FileContentTable> mapContentFromDb(){
        return (resultSet, i) -> {
            int contentId = Integer.parseInt(resultSet.getString("content_id"));
            String fileId = resultSet.getString("file_id");
            String content = resultSet.getString("content");
            String pushed = resultSet.getString("pushed_at");
            return new FileContentTable(contentId,fileId,content,pushed);
        };
    }


}
