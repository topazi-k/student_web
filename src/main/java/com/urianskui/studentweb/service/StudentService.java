package com.urianskui.studentweb.service;

import com.urianskui.studentweb.contstants.Constants;
import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.repository.StudentRepository;
import com.urianskui.studentweb.service.search_utils.RequestParametersProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getById(long id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll(String search, String orderBy, String page) {
        Specification<Student> studentSpecification = RequestParametersProcessor.parseStudentSearchParam(search);
        Sort sort = orderBy == null
                ? Sort.by(Constants.STUDENT_ORDER_BY)
                : RequestParametersProcessor.parseSortParam(orderBy);
        PageRequest pageRequest = page == null
                ? PageRequest.of(0, Constants.PAGE_SIZE, sort)
                : RequestParametersProcessor.createPageRequest(page, sort);

        Page<Student> studentPage = studentRepository.findAll(studentSpecification, pageRequest);
        return studentPage.getContent();
    }
}
