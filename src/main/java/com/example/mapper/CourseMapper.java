package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Course;

@Mapper
public interface CourseMapper {
    public List<Course> findAll();
    
// lesson02
    // 新規登録
    public void insert(Course course);
    
    public Course findById(Integer id);
    // 更新
    public void update(Course course);
    
    // 削除
    public void deleteById(Integer id);
    
    // 複数引数
    public List<Course> findAll(@Param("courseId") Integer courseId, @Param("courseName") String courseName);
}