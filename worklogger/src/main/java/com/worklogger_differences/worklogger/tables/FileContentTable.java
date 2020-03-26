package com.worklogger_differences.worklogger.tables;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/*________________________________________________
  __contentId__|__fileId__|__content__|__timestamp
 */
@Entity
@Table(name="file_content")
public class FileContentTable implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="content_id")
    private long contentId;
    @Column(name="file_id")
    @NotEmpty(message = "Must have a file ID")
    private String fileId;
    @Column(name="content")
    @NotEmpty(message = "Must have a Content")
    private String content;
    @Column(name="pushed_at")
    @NotEmpty(message = "Must have a Timestamp")
    private String pushedAt;
    public FileContentTable(){
        super();
    }
    public FileContentTable( @JsonProperty("content_id") long id,
                             @JsonProperty("file_id") String fileId,
                             @JsonProperty("content") String content,
                             @JsonProperty("pushed_at") String pushedAt){
        this.pushedAt=pushedAt;
        this.contentId = id;
        this.fileId=fileId;
        this.content=content;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public long getContentId() {
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
