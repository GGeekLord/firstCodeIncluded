package com.ecommerceapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long CategoryId;

    private String CategoryName;

//    public Category() {
//    }
//
//    public Category(Long CategoryId) {
//
//        this.CategoryId = CategoryId;
//    }
//
//    public Category(Long CategoryId, String CategoryName) {
//
//        this.CategoryId = CategoryId;
//        this.CategoryName = CategoryName;
//    }
//
//
//    public Long getCategoryId() {
//        return CategoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.CategoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return CategoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.CategoryName = categoryName;
//    }

    @Override
    public String toString() {
        return "%s: [Id: %s || Name: %s]"
                .formatted(getClass().getSimpleName(), getCategoryId(), getCategoryName());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();


        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();


        if (thisEffectiveClass != oEffectiveClass) return false;
        Category category = (Category) o;

        return getCategoryId() != null && Objects.equals(getCategoryId(), category.getCategoryId());
    }

    @Override
    public final int hashCode() {

        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
