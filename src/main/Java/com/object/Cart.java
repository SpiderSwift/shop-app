package com.object;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "t_cart")
public class Cart implements Serializable {

    private User owner;
    private Set<CartProduct> cartProducts;
    private Integer cartId;

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (CartProduct product : cartProducts) {
//            sb.append(product.getProduct());
//        }
//        return sb.toString();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return cartId != null ? cartId.equals(cart.cartId) : cart.cartId == null;
    }

    @Override
    public int hashCode() {
        return cartId != null ? cartId.hashCode() : 0;
    }

    @OneToOne
    @JoinColumn(name = "f_username")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "cartProductId.cart", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
