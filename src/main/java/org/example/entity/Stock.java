// package org.example.entity;

// import javax.persistence.*;

// import java.math.BigDecimal;
// import java.util.Date;

// @Entity
// @Table(name = "stock")
// public class Stock {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "id_composant", nullable = false)
//     private Composant composant;

//     @Column(name = "date_creation")
//     private Date dateCreation;

//     @Column(name = "qtte_stock")
//     private BigDecimal qtteStock;

//     @Column(name = "nombre_jour_conservation")
//     private Integer nombre_jour_conservation;

//     public Stock() {}

//     public Stock(Composant composant, Date dateCreation, BigDecimal qtteStock, Integer nombre_jour_conservation) {
//         this.composant = composant;
//         this.dateCreation = dateCreation;
//         this.qtteStock = qtteStock;
//         this.nombre_jour_conservation = nombre_jour_conservation;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Composant getComposant() {
//         return composant;
//     }

//     public void setComposant(Composant composant) {
//         this.composant = composant;
//     }

//     public Date getdateCreation() {
//         return dateCreation;
//     }

//     public void setdateCreation(Date dateCreation) {
//         this.dateCreation = dateCreation;
//     }

//     public BigDecimal getQtteStock() {
//         return qtteStock;
//     }

//     public void setQtteStock(BigDecimal qtteStock) {
//         this.qtteStock = qtteStock;
//     }

//     public Integer getNombre_jour_conservation() {
//         return nombre_jour_conservation;
//     }

//     public void setNombre_jour_conservation(Integer nombre_jour_conservation) {
//         this.nombre_jour_conservation = nombre_jour_conservation;
//     }

// }