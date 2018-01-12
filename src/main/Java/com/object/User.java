package com.object;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User implements Serializable {

    private String userName;
    private String userPass;
    private Role role;
    private List<Order> orders;
    private Cart cart;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPass != null ? !userPass.equals(user.userPass) : user.userPass != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return cart != null ? cart.equals(user.cart) : user.cart == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        return result;
    }


    //    @Override
//    public String toString() {
//        return "User{" +
//                "userName='" + userName + '\'' +
//                ", userPass='" + userPass + '\'' +
//                ", role=" + role +
//                ", cart=" + cart +
//                '}';
//    }

    @Id
    @Column(name = "f_username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "f_password")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @ManyToOne
    @JoinColumn(name = "f_role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToOne(mappedBy = "owner")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
