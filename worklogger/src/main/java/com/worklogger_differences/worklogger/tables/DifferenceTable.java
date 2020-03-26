package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

/*_______________________________________
  __differenceId__|__fileId__|__fileCOntentId__|__fileContent2Id__|__differences__

 */
@Entity
@Table(name="differences")
public class DifferenceTable {
    @Id
    @Column(name="difference_id")
    private final int id;
    @Column(name="file_id")
    private final String fileId;
    @Column(name="content_one_id")
    private final int contentOne;
    @Column(name="content_two_id")
    private final int contentTwo;
    @Column(name="differences")
    private final String differences;
    @Column(name="group_id")
    private final int groupId;
    public DifferenceTable(){
        this.contentOne=-1;
        this.contentTwo=-2;
        this.id=-1;
        this.fileId=null;
        this.differences=null;
        this.groupId=-1;
    }

    public DifferenceTable(@JsonProperty("difference_id") int id,
                           @JsonProperty("file_id") String fileId,
                           @JsonProperty("content_one_id")int contentOne,
                           @JsonProperty("content_two_id") int contentTwo,
                           @JsonProperty("group_id" )int groupId,
                           @JsonProperty("differences") String differences){
        this.id=id;
        this.fileId=fileId;
        this.contentTwo=contentTwo;
        this.groupId=groupId;
        this.contentOne=contentOne;
        this.differences=differences;
    }

    public int getId() {
        return id;
    }

    public String getFileId() {
        return fileId;
    }

    public int getContentOne() {
        return contentOne;
    }

    public int getContentTwo() {
        return contentTwo;
    }

    public String getDifferences() {
        return differences;
    }
}
