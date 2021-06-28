package com.clothesstore.api.Controllers;

import java.math.BigDecimal;
import java.util.Optional;

import com.clothesstore.api.Models.Products;
import com.clothesstore.api.Services.ProductsService;
import com.clothesstore.api.upload.StorageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @Autowired
   private StorageService storageService;

    /**
     * Obtenemos todos los productos
     * 
     * @return 404 si no hay productos, 200 y lista de productos si hay uno o más
     */
    @GetMapping()
    public ResponseEntity<?> getProducts() {
        return productsService.getProducts();
    }

    /**
     * Insertamos un nuevo producto
     * 
     * @param name
     * @return 201 y el producto insertado
     */// @RequestBody Products products

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveProducts( /*@RequestPart("nuevo")Products newProduct,*/
    @RequestParam String name,
    @RequestParam String description,
    @RequestParam BigDecimal price,
    @RequestParam double discount_rate,
    @RequestPart("imageFront") MultipartFile imageFront, @RequestPart("imageaRear") MultipartFile imageaRear) {
        String urlImageFront = null;
        String urlImageaRear = null;
        if (!imageFront.isEmpty() && !imageaRear.isEmpty()) {
            urlImageFront = storageService.store(imageFront);
            urlImageaRear = storageService.store(imageaRear);
            urlImageFront = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", urlImageFront, null).build().toUriString();
            urlImageaRear = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", urlImageaRear, null).build().toUriString();
        }
        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Products newProduct = new Products();
        newProduct.setUrlImageFront(urlImageFront);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setDiscount_rate(discount_rate);
        newProduct.setPrice(price);
        newProduct.setUrlImageaRear(urlImageFront);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProducts(newProduct));
    }
    /**
     * Obtenemos un producto en base a su ID
     * 
     * @param id
     * @return 404 si no encuentra el producto, 200 y el producto si lo encuentra
     */
    @GetMapping(path = "/{id}")
    public <Optional> Products getProductById(@PathVariable long id) {
        return productsService.getProductById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Products> UpdateProducts(@PathVariable("id") long id,
    @RequestParam String name,
    @RequestParam String description,
    @RequestParam BigDecimal price,
    @RequestParam double discount_rate,
    @RequestPart("imageFront") MultipartFile imageFront, @RequestPart("imageaRear") MultipartFile imageaRear) {
        String urlImageFront = null;
        String urlImageaRear = null;
        if (!imageFront.isEmpty() && !imageaRear.isEmpty()) {
            urlImageFront = storageService.store(imageFront);
            urlImageaRear = storageService.store(imageaRear);
            urlImageFront = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", urlImageFront, null).build().toUriString();
            urlImageaRear = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", urlImageaRear, null).build().toUriString();
        }
        Products newProduct = new Products();
        newProduct.setUrlImageFront(urlImageFront);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setDiscount_rate(discount_rate);
        newProduct.setPrice(price);
        newProduct.setUrlImageaRear(urlImageFront);
        return ResponseEntity.status(HttpStatus.OK).body(productsService.updateProducts(newProduct));
    }

    @GetMapping("/name")
    public ResponseEntity<?> getProductByName(@RequestParam("name") String name) {
        return this.productsService.getProductByName(name);
    }
    
    @GetMapping(path = "MostManted")
    public ResponseEntity<?> getMostWantedProducts() {
        return productsService.getMostWantedProducts();
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delecteProduct(@PathVariable("id") long id) {
        return this.productsService.delecteProductById(id);

    }

}
