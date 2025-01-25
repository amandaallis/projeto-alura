package br.com.alura.ProjetoAlura.course.repository;

import br.com.alura.ProjetoAlura.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);

    Optional<Course> findByCodeAndInactivateDateIsNull(String code);

    @Modifying
    @Query("UPDATE Course c SET c.inactivateDate = CURRENT_TIMESTAMP, " +
            "c.status = 'INACTIVE' " +
            "WHERE c.code = :code " +
            "AND c.inactivateDate IS NULL")
    void updateInactivateData(String code);
}
