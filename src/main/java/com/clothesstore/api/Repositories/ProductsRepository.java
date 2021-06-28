package com.clothesstore.api.Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.clothesstore.api.Models.Products;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {
    public abstract ArrayList<Products> findByName(String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="UPDATE products SET search_amount = search_amount + 1 WHERE id = ?1")
    public abstract void setSearchAmountById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="SELECT *FROM ( SELECT row_number() OVER (ORDER BY search_amount DESC) as posicion_destacado, "+
    "id, description, name,price, discount_rate, search_amount, url_image_front , url_imagea_rear FROM products ) as SalaryCTE LIMIT 4")
    public abstract List<Products> getMostWantedProducts();
    
   /* @Modifying
    @Query("UPDATE products SET search_amount = search_amount + 1 WHERE name = ?")
    public abstract void setSearchAmountByName(String name);
*/

   // public abstract ArrayList<Products> findProductMostWanted();

}
