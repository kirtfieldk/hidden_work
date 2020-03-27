package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.DeleteTable;
import com.worklogger_differences.worklogger.tables.FileContentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteRepository extends JpaRepository<DeleteTable, Long> {
}
