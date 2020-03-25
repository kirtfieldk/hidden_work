package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepository extends JpaRepository<FileContentTable, Long> {
}
