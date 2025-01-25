package br.com.alura.ProjetoAlura.registration.repository;

import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportDTO;
import br.com.alura.ProjetoAlura.registration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("""
            SELECT re FROM Registration re
            WHERE re.courseId.id = :courseId
            AND re.userId.id = :userId
            """)
    List<Registration> findByCourseIdAndUserId(Long courseId, Long userId);

    @Query(value = """
            SELECT
                re.courseId as courseId,
                c.code as courseCode,
                c.name as courseName,
                u.name as instructorName,
                u.email as instructorEmail,
            COUNT(*) AS totalRegistrations
            FROM Registration re
            INNER JOIN Course c ON c.id = re.courseId
            inner join user u on u.id = c.instructorId\s
            WHERE c.inactivateDate IS NULL
            GROUP BY re.courseId
            ORDER BY totalRegistrations DESC;

            """, nativeQuery = true)
    List<RegistrationReportDTO> findRankingCoursesRegistered();
}