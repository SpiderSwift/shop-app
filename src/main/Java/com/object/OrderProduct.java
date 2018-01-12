package com.object;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order_product")
@AssociationOverrides( {
        @AssociationOverride(name = "orderProductId.order", joinColumns = @JoinColumn(name = "f_order_id")),
        @AssociationOverride(name = "orderProductId.product", joinColumns = @JoinColumn(name = "f_product_id"))
} )
public class OrderProduct implements Serializable {

    private OrderProductId orderProductId;
    private Integer number;

//    @Override
//    public String toString() {
//        return "OrderProduct{" +
//                "number=" + number +
//                '}';
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        return orderProductId != null ? orderProductId.equals(that.orderProductId) : that.orderProductId == null;
    }

    @Override
    public int hashCode() {
        return orderProductId != null ? orderProductId.hashCode() : 0;
    }

    @EmbeddedId
    public OrderProductId getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(OrderProductId primaryKey) {
        this.orderProductId = primaryKey;
    }

    @Column(name = "f_number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Transient
    public Order getOrder() {
        return orderProductId.getOrder();
    }

    public void setOrder(Order order) {
        orderProductId.setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return orderProductId.getProduct();
    }

    public void setProduct(Product product) {
        orderProductId.setProduct(product);
    }

}
