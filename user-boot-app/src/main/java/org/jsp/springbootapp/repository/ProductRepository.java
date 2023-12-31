package org.jsp.springbootapp.repository;

import java.util.List;

import org.jsp.springbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.user.id=?1")
List<Product> findProductsByUserId(int user_id);
}
