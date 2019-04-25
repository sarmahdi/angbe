package io.swagger.model.repository;

import io.swagger.model.ToDoItem;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/***
 * We dont need a RestResource as we need to defione the endpoints ourselves
 */
@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
@Repository
public interface TodoItemRepository extends JpaRepository<ToDoItem, Long> {

}
