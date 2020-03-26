package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.exception.MissingParamsException;
import com.worklogger_differences.worklogger.repository.DifferenceRepository;
import com.worklogger_differences.worklogger.repository.FileContentRepository;
import com.worklogger_differences.worklogger.repository.FileRepository;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DbService implements DbManipluationInterface{
    private final JdbcTemplate jdbc;
    @Autowired
    private DifferenceRepository differenceRepository;
    @Autowired
    private FileContentRepository fileContentRepository;
    @Autowired
    private FileRepository fileRepository;
    public DbService(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public List<FileContentTable> fetchAllFileContent() {
        String stm = "SELECT * FROM file_content;";
        return jdbc.query(stm, mapContentFromDb());
    }

    @Override
    public List<DifferenceTable> fetchAllDifferences() {
        String stm = "SELECT * FROM differences;";
        return jdbc.query(stm, mapDifferenceFromDb());
    }

    @Override
    public List<FilesTable> fetchAllFiles() {
        String stm = "SELECT * FROM files;";
        return jdbc.query(stm, mapFilesFromDb());
    }

    @Override
    public FileContentTable fetchFileContentById(long id) throws FileNotFoundInDbException {
        String stm = "SELECT* FROM file_content where content_id="+id+";";
        List<FileContentTable> file = jdbc.query(stm, mapContentFromDb());
        if (!file.isEmpty())
            return file.get(0);
        else
            throw new FileNotFoundInDbException("File Contents not found in DB with id:" + id);
    }

    @Override
    public List<String> findDifferenceBetweenTwoFiles
    (FileContentTable one, FileContentTable two) throws CompareDifferentFilesException {
        if(!one.getFileId().equals(two.getFileId()))
            throw new CompareDifferentFilesException("Files are not historically the same");
        List<String> returnString = new ArrayList<String>();
        String[] fileOneLines = one.getContent().split("\n");
        String[] fileTwoLines = two.getContent().split("\n");
        int minLength = fileOneLines.length;
        String[] maxSizeArray = fileTwoLines.clone();
        if (minLength > fileTwoLines.length)
            maxSizeArray = fileOneLines.clone();
            minLength = fileTwoLines.length;
        //This adds the difference between files//
        for(int i = 0; i < minLength; i++){
            if(!fileOneLines[i].trim().equals(fileTwoLines[i].trim()) && fileOneLines.length != 0){
                returnString.add(fileOneLines[i]);
            }
        }
        //This add the excess of the larger file//
        for(int j = minLength; j < maxSizeArray.length; j++){
            returnString.add(maxSizeArray[j]);
        }
        return returnString;

    }
    @Override
    public ReturnMessage displayDifferenceBetweenFiles(FileContentTable one, FileContentTable two)
    throws CompareDifferentFilesException{
        /////////////////ERROR//////////////////////
        if(!one.getFileId().equals(two.getFileId()))
            throw new CompareDifferentFilesException("Files are not historically the same");
        ////////////////////////////////////////////////////////
        return new ReturnMessage("Difference between files", 202,
                    findDifferenceBetweenTwoFiles(one,two));
    }

    @Override
    public DifferenceTable createDifferenceObject(FileContentTable fileOne, FileContentTable fileTwo, String dif) {
        DifferenceTable temp = new DifferenceTable();
        temp.setContentOne(fileOne.getContentId());
        temp.setContentTwo(fileTwo.getContentId());
        temp.setDifferences(dif);
        temp.setFileId(fileOne.getFileId());
        temp.setGroupId(2);
        differenceRepository.save(temp);
        return temp;
    }

    @Override
    public ReturnMessage saveFileToDb(FilesTable file) throws MissingParamsException {
        if(file.getFileName().equals(null) || file.getId().equals(null) || file.getProject().equals(null)
            || file.getRepository().equals(null))
            throw new MissingParamsException("Invalid File Object");
        fileRepository.save(file);
        return new ReturnMessage("Added: " +file.getFileName(), 202);
    }

    @Override
    public ReturnMessage saveFileContentToDb(FileContentTable fileContent) throws MissingParamsException{
        if(fileContent.getFileId().equals(null) || fileContent.getContent().equals(null))
            throw new MissingParamsException("Invalid FileContent Object");
        fileContentRepository.save(fileContent);
        return new ReturnMessage("Added: " +fileContent.getFileId(), 202);
    }

    @Override
    public ReturnMessage saveDiffToDb(DifferenceTable dif){
        differenceRepository.save(dif);
        return new ReturnMessage("Diff noted: " + dif.getFileId(), 202);
    }

    @Override
    public Boolean fileInDb(String fileId) {
        String stm = "SELECT * FROM files WHERE file_id='"+fileId+"';";
        List<FilesTable> files = jdbc.query(stm, mapFilesFromDb());
        return !files.isEmpty();
    }

    @Override
    public Boolean fileContentInDb(long id){
        String stm = "SELECT * FROM file_content WHERE file_id="+id+";";
        List<FilesTable> files = jdbc.query(stm, mapFilesFromDb());
        return !files.isEmpty();
    }
}
