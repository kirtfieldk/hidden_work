package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.DifferenceTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DifferenceRepository extends JpaRepository<DifferenceTable, Long> {
    List<DifferenceTable> findAll();
}
