package ro.r3ea.primavara2.date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Category;

@Repository
public interface DateCategoryPaginare extends PagingAndSortingRepository<Category, Integer>{

}
