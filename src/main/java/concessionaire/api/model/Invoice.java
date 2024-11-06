package concessionaire.api.model;

import java.util.Date;

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
@Table(name = "factura")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_asesor", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "id_carro", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_tienda", nullable = false)
    private Store store;

    @Column(name = "identificacion_factura", length = 20, nullable = false)
    private String invoiceId;

    @Column(name = "fecha", nullable = false)
    private Date date;

    @Column(name = "cantidad", nullable = false)
    private Integer qty;

    @Column(name = "tipo_pago", length = 10, nullable = false)
    private String paymentType;

    @Column(name = "estado_pago", length = 10, nullable = false)
    private String paymentStatus;
}
