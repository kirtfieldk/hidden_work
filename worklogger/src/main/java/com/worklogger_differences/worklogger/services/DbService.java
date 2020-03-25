package com.worklogger_differences.worklogger.services;

import com.worklogger_differences.worklogger.repository.DifferenceRepository;
import com.worklogger_differences.worklogger.repository.FileContentRepository;
import com.worklogger_differences.worklogger.repository.FileRepository;
import com.worklogger_differences.worklogger.returnMessage.ReturnMessage;
import com.worklogger_differences.worklogger.tables.DifferenceTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DbService implements DbManipluationInterface{
    private final JdbcTemplate jdbc;
    private final DifferenceRepository differenceRepository;
    private final FileContentRepository fileContentRepository;
    private final FileRepository fileRepository;
    public DbService(JdbcTemplate jdbc, DifferenceRepository differenceRepository,
                     FileContentRepository fileContentRepository, FileRepository fileRepository){
        this.fileContentRepository=fileContentRepository;
        this.differenceRepository=differenceRepository;
        this.fileRepository=fileRepository;
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
    public FileContentTable fetchFileContentById(int id) {
        String stm = "SELECT* FROM file_content where content_id="+id+";";
        List<FileContentTable> file = jdbc.query(stm, mapContentFromDb());
        if (!file.isEmpty())
            return file.get(0);
        return null;
    }

    @Override
    public List<String> findDifferenceBetweenTwoFiles(FileContentTable one, FileContentTable two) {
        List<String> returnString = new ArrayList<String>();
        String[] fileOneLines = one.getContent().split("\n");
        String[] fileTwoLines = two.getContent().split("\n");
        int minLength = fileOneLines.length;
        if (minLength > fileTwoLines.length)
            minLength = fileTwoLines.length;
        for(int i = 0; i < minLength; i++){
            if(!fileOneLines[i].trim().equals(fileTwoLines[i].trim()) && fileOneLines.length != 0){
                returnString.add(fileOneLines[i]);
            }
        }
        return returnString;
    }

    @Override
    public DifferenceTable createDifferenceObject(FileContentTable fileOne, FileContentTable fileTwo, List<String> dif) {

        return new DifferenceTable(1, fileOne.getFileId(), fileOne.getContentId(), fileTwo.getContentId(), dif );
    }

    @Override
    public ReturnMessage saveFileToDb(FilesTable file) {
        fileRepository.save(file);
        return new ReturnMessage("Added: " +file.getFileName(), 202);
    }

    @Override
    public ReturnMessage saveFileContentToDb(FileContentTable fileContent) {
        fileContentRepository.save(fileContent);
        return new ReturnMessage("Added: " +fileContent.getFileId(), 202);
    }

    @Override
    public ReturnMessage saveDiffToDb(DifferenceTable dif) {
        differenceRepository.save(dif);
        return new ReturnMessage("Diff noted: " + dif.getFileId(), 202);
    }
}
