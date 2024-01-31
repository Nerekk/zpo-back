package com.project.zpo.Repositories;

import com.project.zpo.Tables.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Attendance> {
}
