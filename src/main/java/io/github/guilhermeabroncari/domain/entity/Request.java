package io.github.guilhermeabroncari.domain.entity;

import io.github.guilhermeabroncari.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REQUEST")
@Table(name = "REQUEST")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    @Column(name = "REQUEST_DATE")
    private LocalDate requestDate;
    @Column(name = "AMOUNT", precision = 20, scale = 2)
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
