package com.project.zpo.Repositories;

import com.project.zpo.Tables.Group;
import com.project.zpo.Tables.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.name = :name")
    Group findByName(@PathVariable("name") String name);

}
