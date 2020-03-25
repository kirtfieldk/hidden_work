package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.FilesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FilesTable, Long> {

}
