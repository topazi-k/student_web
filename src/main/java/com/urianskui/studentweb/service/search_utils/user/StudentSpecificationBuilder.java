package com.urianskui.studentweb.service.search_utils.user;

import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.service.search_utils.SearchCriteria;
import com.urianskui.studentweb.service.search_utils.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecificationBuilder {

    private List<SearchCriteria> params= new ArrayList<>();

    public StudentSpecificationBuilder with(String key, String operation, Object value) {
        SearchOperation searchOperation = SearchOperation.getSimpleOperation(operation.charAt(0));
        params.add(new SearchCriteria(key, searchOperation, value));
        return this;
    }

    public Specification<Student> build() {
        if (params.size() == 0) {
            return null;
        }
        Specification result = new StudentSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new StudentSpecification(params.get(i)))
                    : Specification.where(result).and(new StudentSpecification(params.get(i)));
        }
        return result;
    }
}
