package io.github.guilhermeabroncari.domain.entity;

import io.github.guilhermeabroncari.domain.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REQUEST")
@Table(name = "REQUESTS")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    @Column(name = "REQUEST_DATE")
    private LocalDate requestDate;
    @Column(name = "TOTAL_PRICE", precision = 20, scale = 2)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private RequestStatus status;
    @OneToMany(mappedBy = "request")
    private List<ItemRequest> itemRequestList;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", client=" + client +
                ", requestDate=" + requestDate +
                ", total=" + totalPrice +
                ", itemRequestList=" + itemRequestList +
                '}';
    }
}
