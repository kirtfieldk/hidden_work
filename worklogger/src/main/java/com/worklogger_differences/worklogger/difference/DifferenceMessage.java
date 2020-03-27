package com.worklogger_differences.worklogger.difference;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worklogger_differences.worklogger.tables.DeleteTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import com.worklogger_differences.worklogger.tables.FilesTable;
import com.worklogger_differences.worklogger.tables.InsertTable;

import java.util.List;

public class DifferenceMessage {
    private final List<InsertTable> insertions;
    private final List<DeleteTable> deletion;
    private final FilesTable file;
    private final FileContentTable oldFileContent;
    private final FileContentTable newFileContent;

    public DifferenceMessage(
            @JsonProperty("file") FilesTable file,
            @JsonProperty("old_commit") FileContentTable oldFileContent,
            @JsonProperty("commit") FileContentTable newFileContent,
            @JsonProperty("insertion") List<InsertTable> insertions,
            @JsonProperty("deleted") List<DeleteTable> deletion
    ){
       this.file=file;
       this.oldFileContent=oldFileContent;
       this.deletion=deletion;
       this.insertions=insertions;
       this.newFileContent=newFileContent;
    }

    public List<InsertTable> getInsertions() {
        return insertions;
    }

    public List<DeleteTable> getDeletion() {
        return deletion;
    }

    public FilesTable getFile() {
        return file;
    }

    public FileContentTable getOldFileContent() {
        return oldFileContent;
    }

    public FileContentTable getNewFileContent() {
        return newFileContent;
    }
}
