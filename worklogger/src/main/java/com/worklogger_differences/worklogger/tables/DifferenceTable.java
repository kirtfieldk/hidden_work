package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
/*_______________________________________
  __differenceId__|__fileId__|__fileCOntentId__|__fileContent2Id__|__differences__

 */
@Entity
@Table(name="differences")
public class DifferenceTable  implements Serializable {
    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="difference_id")
    private long id;
    @Column(name="file_id")
    private String fileId;
    @Column(name="content_one_id")
    private long contentOne;
    @Column(name="content_two_id")
    private long contentTwo;
    @Column(name="differences")
    private String differences;
    @Column(name="group_id")
    private int groupId;

    public DifferenceTable(){}

    public DifferenceTable(@JsonProperty("difference_id") long id,
                           @JsonProperty("file_id") String fileId,
                           @JsonProperty("content_one_id")long contentOne,
                           @JsonProperty("content_two_id") long contentTwo,
                           @JsonProperty("group_id" )int groupId,
                           @JsonProperty("differences") String differences){
        this.id=id;
        this.fileId=fileId;
        this.contentTwo=contentTwo;
        this.groupId=groupId;
        this.contentOne=contentOne;
        this.differences=differences;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setContentOne(long contentOne) {
        this.contentOne = contentOne;
    }

    public void setContentTwo(long contentTwo) {
        this.contentTwo = contentTwo;
    }

    public void setDifferences(String differences) {
        this.differences = differences;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public String getFileId() {
        return fileId;
    }

    public long getContentOne() {
        return contentOne;
    }

    public long getContentTwo() {
        return contentTwo;
    }

    public String getDifferences() {
        return differences;
    }
}
