package com.example.infs3605communitymanagement;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM Course")
    List<Course> getCourses();

    // TODO for Question 1a)
    // Implement code for a second SQL query that returns the list of courses whose faculty matches a faculty param

    // This query searches for courses of a certain faculty, as provided by the getFaculty method
    @Query("SELECT * FROM Course WHERE faculty == :faculty")
    Course getFaculty(String faculty);

    @Query("SELECT * FROM Course WHERE code == :code")
    Course getCourse(String code);

}
