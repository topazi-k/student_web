package com.urianskui.studentweb.service.search_utils;

import com.urianskui.studentweb.exceptions.IllegalRequestParameterException;
import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.service.search_utils.user.StudentSpecificationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParametersProcessor {

    public static Specification<Student> parseStudentSearchParam(String search) {
        StudentSpecificationBuilder builder = new StudentSpecificationBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile("('?\\w+?)(:|!|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(
                        matcher.group(1),
                        matcher.group(2),
                        matcher.group(3));
            }
        }
        return builder.build();
    }

    public static Sort parseSortParam(String orderBy) {
        Pattern pattern = Pattern.compile("(\\w+?)(:asc|:desc)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(orderBy);
        if (matcher.find()) {
            throw new IllegalRequestParameterException("Invalid 'orderBy' request parameter. Try ... orderBy=firstName:desc");
        }
        Sort sort = Sort.by(matcher.group(1));
        if (matcher.group(2).toLowerCase().contains(":desc")) return sort.descending();
        return sort.ascending();
    }

    public static PageRequest createPageRequest(String page, Sort sort) {
        Pattern pattern = Pattern.compile("page:(\\d{1,}),size:(\\d{1,})");
        Matcher matcher = pattern.matcher(page);
        if (!matcher.find()) {
            throw new IllegalRequestParameterException("Invalid 'page' request parameter. Try .... page=page:2,size:40");
        }
        int pageNumber = Integer.parseInt(matcher.group(1));
        int pageSize = Integer.parseInt(matcher.group(2));
        return PageRequest.of(pageNumber, pageSize, sort);

    }
}
