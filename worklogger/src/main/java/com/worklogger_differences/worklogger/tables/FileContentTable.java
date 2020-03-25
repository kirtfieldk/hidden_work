package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*________________________________________________
  __contentId__|__fileId__|__content__|__timestamp
 */
@Entity
@Table(name="file_content")
public class FileContentTable {
    @Id
    @Column(name="content_id")
    private final int contentId;
    @Column(name="file_id")
    private final String fileId;
    @Column(name="content")
    private final String content;
    @Column(name="pushed_at")
    private final String pushedAt;
    public FileContentTable(){
        super();
        this.content=null;
        this.contentId=-1;
        this.fileId=null;
        this.pushedAt=null;
    }
    public FileContentTable(@JsonProperty("content_id") int contentId,
                            @JsonProperty("file_id") String fileId,
                            @JsonProperty("content") String content,
                            @JsonProperty("pushed_at") String pushedAt){
        this.pushedAt=pushedAt;
        this.fileId=fileId;
        this.contentId=contentId;
        this.content=content;
    }

    public int getContentId() {
        return contentId;
    }

    public String getFileId() {
        return fileId;
    }

    public String getContent() {
        return content;
    }

    public String getPushedAt() {
        return pushedAt;
    }
}
