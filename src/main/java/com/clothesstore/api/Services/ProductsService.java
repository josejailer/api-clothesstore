package com.clothesstore.api.Services;

import java.util.List;
import java.util.Optional;

import com.clothesstore.api.Config.Exceptions.ProductoNotFoundException;
import com.clothesstore.api.Models.Products;
import com.clothesstore.api.Repositories.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    public ResponseEntity<?> getProducts() {
        List<Products> result = (List<Products>) productsRepository.findAll();
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos registrados");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    public Products saveProducts(Products products) {
        return productsRepository.save(products);
    }

    public Products updateProducts(Products products) {
        return productsRepository.save(products);
    }

    public <Optional> Products getProductById(long id) {
        try {
            this.setSearchAmountById(id, null);
            return productsRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
        } catch (ProductoNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    public ResponseEntity<?> getProductByName(String name) {
        List<Products> result = (List<Products>) productsRepository.findByName(name);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos con ese nombre");
        } else {
            this.setSearchAmountById(0, result);
            return ResponseEntity.ok(result);
        }
    }

    public void setSearchAmountById(long id, List<Products> listProducts) {
        if (id != 0) {
            productsRepository.setSearchAmountById(id);
        } else if (!listProducts.isEmpty() && listProducts != null) {

            for (int i = 0; i < listProducts.size(); i++) {
                productsRepository.setSearchAmountById(listProducts.get(i).getId());
            }
        }
    }

    public ResponseEntity<?> getMostWantedProducts() {
        List<Products> result = productsRepository.getMostWantedProducts();
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos registrados");
        } else {
            return ResponseEntity.ok(result);
        }

    }

    public ResponseEntity<?> delecteProductById(Long id) {
        Products producto = productsRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
        productsRepository.delete(producto);
        return ResponseEntity.noContent().build();
    }

}
