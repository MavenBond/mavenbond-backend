package com.mavenbond.businessservice.domain;

import com.mavenbond.businessservice.dto.SearchOperation;
import com.mavenbond.businessservice.dto.SpecSearchCriteria;
import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.model.Offer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationsBuilder {
    private final List<SpecSearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final SpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final SpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }


    public Specification<Event> buildEvent() {
        if (params.size() == 0)
            return null;

        Specification<Event> result = new EventSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new EventSpecification(params.get(i)))
                    : Specification.where(result).and(new EventSpecification(params.get(i)));
        }

        return result;
    }

    public Specification<Offer> buildOffer() {
        if (params.size() == 0)
            return null;

        Specification<Offer> result = new OfferSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new OfferSpecification(params.get(i)))
                    : Specification.where(result).and(new OfferSpecification(params.get(i)));
        }

        return result;
    }

//    public final EventSpecificationsBuilder with(EventSpecification spec) {
//        params.add(spec.getCriteria());
//        return this;
//    }
//
//    public final EventSpecificationsBuilder with(SpecSearchCriteria criteria) {
//        params.add(criteria);
//        return this;
//    }
}
