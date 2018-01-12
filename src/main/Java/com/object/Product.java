package com.object;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_product")
public class Product implements Serializable {

    private Integer productId;
    private String name;
    private Integer numberAvailable;
    private Category category;
    private String description;
    private Integer price;
    private List<OrderProduct> orders;
    private List<CartProduct> carts;

    @Override
    public String toString() {
        return name;
    }


    public Product() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (numberAvailable != null ? !numberAvailable.equals(product.numberAvailable) : product.numberAvailable != null)
            return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (numberAvailable != null ? numberAvailable.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Column(name = "f_price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "f_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "f_number_available")
    public Integer getNumberAvailable() {
        return numberAvailable;
    }

    public void setNumberAvailable(Integer numberAvailable) {
        this.numberAvailable = numberAvailable;
    }

    @ManyToOne
    @JoinColumn(name = "f_category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "f_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "orderProductId.product", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProduct> orders) {
        this.orders = orders;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "cartProductId.product", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CartProduct> getCarts() {
        return carts;
    }

    public void setCarts(List<CartProduct> carts) {
        this.carts = carts;
    }
}
