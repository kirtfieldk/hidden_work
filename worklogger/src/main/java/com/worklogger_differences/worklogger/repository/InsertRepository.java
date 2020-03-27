package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.DeleteTable;
import com.worklogger_differences.worklogger.tables.InsertTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsertRepository extends JpaRepository<InsertTable, Long> {
    List<InsertTable> findAll();
}
