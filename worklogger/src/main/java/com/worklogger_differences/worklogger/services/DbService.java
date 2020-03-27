package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.exception.CompareDifferentFilesException;
import com.worklogger_differences.worklogger.exception.FileAlreadyInDb;
import com.worklogger_differences.worklogger.exception.FileNotFoundInDbException;
import com.worklogger_differences.worklogger.repository.*;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
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
    @Autowired
    private DeleteRepository deleteRepository;
    @Autowired
    private InsertRepository insertRepository;
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
    public List<DeleteTable> fetchAllDelete() {
        String stm = "SELECT * FROM deletion;";
        return jdbc.query(stm, mapDeleteFromDb());
    }

    @Override
    public List<InsertTable> fetchAllInsert() {
        String stm = "SELECT * FROM insertion;";
        return jdbc.query(stm, mapInsertFromDb());
    }

    @Override
    public FilesTable fetchFileById(String id) throws FileNotFoundInDbException {
        String stm = "SELECT * FROM files where file_id = '"+id+"';";
        List<FilesTable> files = jdbc.query(stm, mapFilesFromDb());
        if(!files.isEmpty())
            return files.get(0);
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
        String stm = "SELECT * FROM file_content where content_id="+id+";";
        List<FileContentTable> file = jdbc.query(stm, mapContentFromDb());
        if (!file.isEmpty())
            return file.get(0);
        else
            throw new FileNotFoundInDbException("File Contents not found in DB with id:" + id);
    }

    @Override
    public ResponseEntity<InsertTable> fetchInsertById(long id) throws FileNotFoundInDbException{
        String stm = "SELECT * FROM insertion where insertion_id="+id+";";
        List<InsertTable> insert = jdbc.query(stm, mapInsertFromDb());
        if(!insert.isEmpty())
            return new ResponseEntity<InsertTable>(insert.get(0), HttpStatus.OK);
        throw new FileNotFoundInDbException("File Not Found with id: "+id);
    }

    @Override
    public ResponseEntity<DeleteTable> fetchDeleteById(long id) throws FileNotFoundInDbException {
        String stm = "SELECT * FROM deletion where delete_id ="+id;
        List<DeleteTable> del = jdbc.query(stm, mapDeleteFromDb());
        if(!del.isEmpty())
            return new ResponseEntity<DeleteTable>(del.get(0), HttpStatus.OK);
        throw new FileNotFoundInDbException("File Not Found with id: "+id);
    }

    @Override
    public List<DeleteTable> fetchAllDeleteForFileContent(long recent, long older) {
        String stm = "SELECT * FROM deletion where origin = "+older+" and destination =" + recent+";";
        return jdbc.query(stm, mapDeleteFromDb());
    }
    @Override
    public List<InsertTable> fetchAllInsertForFile(long recent, long older){
        String stm = "SELECT * FROM insertion where origin = "+recent+" and destination =" + older+";";
        return jdbc.query(stm, mapInsertFromDb());
    }

    @Override
    public ResponseEntity<String> findDifferenceBetweenTwoFilesRecursive(String[] latest, String fileId,
                                                                         String[] oldest, long source, long dest,
                                                                         int index) {
        if(index >= latest.length || index >= oldest.length) {
            if(latest.length > oldest.length){
                for(int i = index; i < latest.length; i++){
                    InsertTable in = InsertTable.addTraitsToInsert(fileId,dest,source,i, latest[i]);
                    saveInsertToDb(in);
                }
            }else if(oldest.length >latest.length){
                for(int i = index; i < oldest.length; i++){
                    DeleteTable in = DeleteTable.addTraitsToDel(fileId,source,dest,i, oldest[i]);
                    saveDeleteToDb(in);
                }
            }
            return new ResponseEntity<String>("Finish", HttpStatus.OK);
        }
        if(!latest[index].trim().equals(oldest[index].trim())){
            DeleteTable del = DeleteTable.addTraitsToDel(fileId,source,dest,index, oldest[index]);
            InsertTable in = InsertTable.addTraitsToInsert(fileId,dest,source,index, latest[index]);
            saveDeleteToDb(del);
            saveInsertToDb(in);
        }
        return findDifferenceBetweenTwoFilesRecursive(latest, fileId, oldest, source, dest, index+=1);
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
    @Override
    public ResponseEntity<DeleteTable> saveDeleteToDb(DeleteTable del){
        deleteRepository.save(del);
        return new ResponseEntity<DeleteTable>(del, HttpStatus.OK);
    }
    public ResponseEntity<InsertTable> saveInsertToDb(InsertTable insert){
        insertRepository.save(insert);
        return new ResponseEntity<InsertTable>(insert, HttpStatus.OK);
    }
}
