package com.example.presentation;

import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Presentation2 {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;

    public void run() {
        Product product = new Product();
        product.setName("Produit 1");
        product.setPrice(100.0);
        productDao.create(product);

        Product product2 = new Product();
        product2.setName("Produit 2");
        product2.setPrice(140.0);
        productDao.create(product2);

        Product product3 = new Product();
        product3.setName("Produit 3");
        product3.setPrice(178.0);
        productDao.create(product3);

        List<Product> produits = new ArrayList<>();
        produits.add(product);
        produits.add(product2);
        produits.add(product3);

        Category category = new Category();
        category.setLibelle("Categorie 1");
        category.setCode("ESZ87R");
        category.setProducts(produits);
        categoryDao.create(category);

        System.out.println("Produit sauvegardé : " + product.getName());
        System.out.println("Categorie sauvegardé : " + category.getLibelle());
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(com.example.util.HibernateConfig.class);

        Presentation2 presentation = context.getBean(Presentation2.class);
        presentation.run();

        context.close();
    }
}
