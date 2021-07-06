package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.Category;
import foodapp.springbootbackend.model.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class MenuCustomQueriesImpl implements MenuCustomQueries {

    @PersistenceContext
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public MenuCustomQueriesImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<MenuItem> getMenuItemsLike(String menuItemNameSearchQuery,
                                           String menuItemDescriptionSearchQuery,
                                           String categoriesSearchQuery) {

        CriteriaQuery<MenuItem> query = criteriaBuilder.createQuery(MenuItem.class);
        Root<MenuItem> menuItemRoot = query.from(MenuItem.class);

        query.select(menuItemRoot);

        Predicate namePredicate = getAlwaysTruePredicate();
        if(isValuePresent(menuItemNameSearchQuery))
            namePredicate = buildNamePredicate(menuItemNameSearchQuery,
                    menuItemDescriptionSearchQuery, menuItemRoot);

        Predicate categoryPredicate = getAlwaysTruePredicate();
        if(isValuePresent(categoriesSearchQuery))
            categoryPredicate = buildCategoryPredicate(categoriesSearchQuery,
                    menuItemRoot);

        query.where(criteriaBuilder.and(
           namePredicate,
           categoryPredicate
        ));

        return entityManager.createQuery(query).getResultList();
    }

    private Predicate getAlwaysTruePredicate() {
        return criteriaBuilder.and();
    }

    private Predicate buildNamePredicate(String menuItemNameSearchQuery,
                                         String menuItemDescriptionSearchQuery,
                                         Root<MenuItem> menuItemRoot) {
        if(isValuePresent(menuItemDescriptionSearchQuery)){
            return criteriaBuilder.or(
                criteriaBuilder.like(
                        menuItemRoot.get("name"),
                        "%"+menuItemNameSearchQuery+"%"
                ),
                criteriaBuilder.like(
                        menuItemRoot.get("description"),
                        "%"+menuItemDescriptionSearchQuery+"%"
                )
            );
        }else {
            return criteriaBuilder.like(
                menuItemRoot.get("name"),
                "%"+menuItemNameSearchQuery+"%"
            );
        }
    }

    private Predicate buildCategoryPredicate(String categoriesSearchQuery,
                                             Root<MenuItem> menuItemRoot) {
        List<String> categoriesSearchQueryAsList =
                Arrays.asList(categoriesSearchQuery.split(","));

        Join<MenuItem, Category> categorySetJoin = menuItemRoot
                .join("category", JoinType.INNER);

        return categorySetJoin.get("name").in(categoriesSearchQueryAsList);
    }

    private boolean isValuePresent(String valueToCheck){
        return valueToCheck != null && !valueToCheck.isEmpty();
    }
}
