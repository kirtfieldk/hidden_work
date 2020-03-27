package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/*
       File_id - delete_id - old_file - new_file - string_value
 */
@Entity
@Table(name = "deletion")
public class DeleteTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="delete_id")
    private long deleteId;
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
    public DeleteTable(){
        super();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DeleteTable(@JsonProperty("delete_id") long deleteId,
                       @JsonProperty("file_id") String fileId,
                       @JsonProperty("origin") long oldId,
                       @JsonProperty("destination") long newId,
                       @JsonProperty("index") int index,
                       @JsonProperty("string_value") String value){
        this.fileId=fileId;
        this.deleteId=deleteId;
        this.index=index;
        this.oldId=oldId;
        this.newId=newId;
        this.stringValue=value;
    }

    public long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(long deleteId) {
        this.deleteId = deleteId;
    }

    public String getFileId() {
        return fileId;
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

    public static DeleteTable addTraitsToDel(String fileId, long source, long dest, int index, String value){
        DeleteTable del = new DeleteTable();
        del.setFileId(fileId);
        del.setOldId(source);
        del.setNewId(dest);
        del.setStringValue(value);
        del.setIndex(index);
        return del;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
