package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
/*
       File_id - delete_id - old_file - new_file - string_value
 */
@Entity
@Table(name = "insertion")
public class InsertTable implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="insertion_id")
    private long insertId;
    @Column(name = "file_id")
    private String fileId;
    @Column(name = "origin")
    private long oldId;
    @Column(name = "destination")
    private long newId;
    @Column(name = "index")
    private int index;
    @Column(name = "string_value")
    private String stringValue;
    public InsertTable(){
        super();
    }
    public InsertTable(@JsonProperty("insertion_id") long insertionId,
                       @JsonProperty("file_id") String fileId,
                       @JsonProperty("origin") long oldId,
                       @JsonProperty("destination") long newId,
                       @JsonProperty("index") int index,
                       @JsonProperty("string_value") String value){
        this.fileId=fileId;
        this.insertId=insertionId;
        this.oldId=oldId;
        this.index=index;
        this.newId=newId;
        this.stringValue=value;
    }


    public long getInsertId() {
        return insertId;
    }

    public void setInsertId(long insertId) {
        this.insertId = insertId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFileId() {
        return fileId;
    }
    public static InsertTable addTraitsToInsert(String fileId, long source, long dest, int index, String value){
        InsertTable in = new InsertTable();
        in.setFileId(fileId);
        in.setOldId(source);
        in.setNewId(dest);
        in.setStringValue(value);
        in.setIndex(index);
        return in;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public long getOldId() {
        return oldId;
    }

    public void setOldId(long oldId) {
        this.oldId = oldId;
    }

    public long getNewId() {
        return newId;
    }

    public void setNewId(long newId) {
        this.newId = newId;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
