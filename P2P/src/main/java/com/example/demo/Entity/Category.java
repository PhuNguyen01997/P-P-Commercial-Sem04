package com.example.demo.Entity;




import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Integer id;
    @Column(length = 80)
    private String name;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
    private List<Product> products;
}
