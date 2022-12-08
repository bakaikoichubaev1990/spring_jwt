package com.example.spring_rest_api_session_java7.repository;

import com.example.spring_rest_api_session_java7.entities.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where upper(q.name) like concat('%',:pagination, '%')" +
            " or upper(q.text)  like concat('%',:pagination, '%')  ")
    List<Question> searchPagination(@Param("pagination") String pagination, Pageable pageable);
}