package concessionaire.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "carro")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_marca", nullable = false)
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_gama", nullable = false)
    private Range range;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

    @Column(name = "capacidad", nullable = false)
    private Integer capacity;

    @Column(name = "modelo", nullable = false)
    private Integer model;

    @Column(name = "cilindraje", nullable = false)
    private Double cc;

    @Column(name = "precio", nullable = false)
    private Double price;
}
