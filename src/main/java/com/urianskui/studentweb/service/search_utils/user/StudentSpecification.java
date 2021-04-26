package com.urianskui.studentweb.service.search_utils.user;

import com.urianskui.studentweb.model.Student;
import com.urianskui.studentweb.service.search_utils.SearchCriteria;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@Data
public class StudentSpecification implements Specification<Student> {

    private SearchCriteria searchCriteria;

    public StudentSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            switch (searchCriteria.getOperation()) {
                case EQUALITY:
                    return builder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
                case NEGATION:
                    return builder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue());
                case GREATER_THAN:
                    return builder.greaterThan(root.<String>get(
                            searchCriteria.getKey()), searchCriteria.getValue().toString());
                case LESS_THAN:
                    return builder.lessThan(root.<String>get(
                            searchCriteria.getKey()), searchCriteria.getValue().toString());
                default:
                    return null;
            }
    }

}
