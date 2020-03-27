package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.exception.FileAlreadyInDb;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public ResponseEntity<FilesTable> fetchFileById(String id) throws FileNotFoundInDbException {
        String stm = "SELECT * FROM files where file_id = '"+id+"';";
        List<FilesTable> files = jdbc.query(stm, mapFilesFromDb());
        if(!files.isEmpty())
            return new ResponseEntity<FilesTable>(files.get(0), HttpStatus.OK);
        throw new FileNotFoundInDbException("No file found");

    }

    @Override
    public ResponseEntity<DifferenceTable> fetchDifById(long id) throws FileNotFoundInDbException {
        String stm = "SELECT * FROM differences WHERE difference_id =" + id+";";
        List<DifferenceTable> dif = jdbc.query(stm, mapDifferenceFromDb());
        if(!dif.isEmpty())
            return new ResponseEntity<DifferenceTable>(dif.get(0), HttpStatus.OK);
        else throw new FileNotFoundInDbException("No File found with id: "+id);
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
    public ResponseEntity<ReturnMessage> saveFileToDb(FilesTable file) throws FileAlreadyInDb {
        if(fileInDb(file.getId()))
            throw new FileAlreadyInDb("File already in DB");
        fileRepository.save(file);
        ReturnMessage res = new ReturnMessage("Added: " +file.getFileName(), 202);
        return new ResponseEntity<ReturnMessage>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReturnMessage> saveFileContentToDb(FileContentTable fileContent) throws FileNotFoundInDbException{
        if(!fileInDb(fileContent.getFileId()))
            throw new FileNotFoundInDbException("Invalid FileContent Object");
        fileContentRepository.save(fileContent);
        ReturnMessage res = new ReturnMessage("Added: " +fileContent.getFileId(), 202);
        return new ResponseEntity<ReturnMessage>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReturnMessage> saveDiffToDb(DifferenceTable dif){
        differenceRepository.save(dif);
        ReturnMessage response = new ReturnMessage("Diff noted: " + dif.getFileId(), 202);
        return new ResponseEntity<ReturnMessage>(response, HttpStatus.OK);
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

    @Override
    public ResponseEntity<ReturnMessage> saveManyFilesToDb(List<FilesTable> files) throws FileAlreadyInDb{

        List<FilesTable> newList = files.stream().filter(file-> fileInDb(file.getId())).collect(Collectors.toList());
        if(!newList.isEmpty())
            throw new FileAlreadyInDb("Files Already in db");
        fileRepository.saveAll(files);
        ReturnMessage res = new ReturnMessage("Saved all to Db", 202);
        return new ResponseEntity<ReturnMessage>(res, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ReturnMessage> saveManyFileContentToDb(List<FileContentTable> files) throws FileNotFoundInDbException{
        List<FileContentTable> newList =
                files.stream().filter(file-> fileInDb(file.getFileId())).collect(Collectors.toList());
        if(newList.isEmpty())
            throw new FileNotFoundInDbException("Files Already in db");
        fileContentRepository.saveAll(files);
        ReturnMessage res = new ReturnMessage("Saved all to Db", 202);
        return new ResponseEntity<ReturnMessage>(res, HttpStatus.OK);
    }

}
