package br.com.alura.ProjetoAlura.registration.dto;

public interface RegistrationReportDTO {
     Long getCourseId();
     String getCourseCode();
     String getCourseName();
     String getInstructorName();
     String getInstructorEmail();
     Long getTotalRegistrations();
}
