package com.project.zpo.Repositories;

import com.project.zpo.Tables.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Repository interface for managing Group entities in the database.
 * Extends JpaRepository providing basic CRUD operations for Group entities.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    /**
     * Custom query method to find a group by its name.
     *
     * @param name The name of the group to search for.
     * @return The Group object representing the group with the specified name.
     */
    @Query("SELECT g FROM Group g WHERE g.name = :name")
    Group findByName(@PathVariable("name") String name);

}
