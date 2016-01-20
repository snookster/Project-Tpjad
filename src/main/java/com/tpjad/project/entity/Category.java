package com.tpjad.project.entity;

import org.eclipse.persistence.annotations.PrivateOwned;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="category", orphanRemoval=true, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @PrivateOwned
    private Collection<Product> products = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
