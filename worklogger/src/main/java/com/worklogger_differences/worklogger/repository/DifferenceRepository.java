package com.worklogger_differences.worklogger.repository;

import com.worklogger_differences.worklogger.tables.DifferenceTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifferenceRepository extends JpaRepository<DifferenceTable, Long> {
}
