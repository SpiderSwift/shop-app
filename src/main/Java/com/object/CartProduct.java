package com.object;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "t_cart_product")
@AssociationOverrides( {
        @AssociationOverride(name = "cartProductId.cart", joinColumns = @JoinColumn(name = "f_cart_id")),
        @AssociationOverride(name = "cartProductId.product", joinColumns = @JoinColumn(name = "f_product_id"))
} )
public class CartProduct implements Serializable {

    private CartProductId cartProductId;
    private Integer number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartProduct that = (CartProduct) o;

        if (cartProductId != null ? !cartProductId.equals(that.cartProductId) : that.cartProductId != null)
            return false;
        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        int result = cartProductId != null ? cartProductId.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @EmbeddedId
    public CartProductId getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(CartProductId primaryKey) {
        this.cartProductId = primaryKey;
    }

    @Column(name = "f_number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Transient
    public Cart getCart() {
        return cartProductId.getCart();
    }

    @Transient
    public Product getProduct() {
        return cartProductId.getProduct();
    }

    public void setProduct(Product product) {
        cartProductId.setProduct(product);
    }

    public void setCart(Cart cart) {
        cartProductId.setCart(cart);
    }

}
