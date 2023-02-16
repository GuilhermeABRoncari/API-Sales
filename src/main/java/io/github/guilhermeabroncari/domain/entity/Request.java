package io.github.guilhermeabroncari.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @Column(name = "AMOUNT", precision = 20, scale = 2)
    private BigDecimal amount;
    @OneToMany(mappedBy = "request")
    private List<ItemRequest> itemRequestList;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", client=" + client +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                ", itemRequestList=" + itemRequestList +
                '}';
    }
}
