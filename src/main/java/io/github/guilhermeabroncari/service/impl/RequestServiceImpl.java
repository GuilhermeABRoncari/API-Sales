package io.github.guilhermeabroncari.service.impl;

import io.github.guilhermeabroncari.domain.entity.Client;
import io.github.guilhermeabroncari.domain.entity.ItemRequest;
import io.github.guilhermeabroncari.domain.entity.Product;
import io.github.guilhermeabroncari.domain.entity.Request;
import io.github.guilhermeabroncari.domain.enums.RequestStatus;
import io.github.guilhermeabroncari.domain.repository.ClientRepository;
import io.github.guilhermeabroncari.domain.repository.ItemRequestRepository;
import io.github.guilhermeabroncari.domain.repository.ProductRepository;
import io.github.guilhermeabroncari.domain.repository.RequestRepository;
import io.github.guilhermeabroncari.exceptions.BusinessRuleException;
import io.github.guilhermeabroncari.exceptions.RequestNotFoundException;
import io.github.guilhermeabroncari.rest.dto.ItemRequestDTO;
import io.github.guilhermeabroncari.rest.dto.RequestDTO;
import io.github.guilhermeabroncari.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final RequestRepository requestRepository;
    private final ItemRequestRepository itemRequestRepository;

    @Override
    @Transactional
    public Request save(RequestDTO dto) {
        Long idClient = dto.getClient_id();
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new BusinessRuleException("Invalid client code." + idClient));
        Request request = new Request();
        request.setTotalPrice(dto.getTotalPrice());
        request.setRequestDate(LocalDate.now());
        request.setClient(client);
        request.setStatus(RequestStatus.CONCLUDED);

        List<ItemRequest> itemsRequest = convertItems(request, dto.getItems());
        requestRepository.save(request);
        itemRequestRepository.saveAll(itemsRequest);
        request.setItemRequestList(itemsRequest);

        return request;
    }

    @Override
    public Optional<Request> getCompositeRequest(Long id) {
        return requestRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void statusUpdate(Long id, RequestStatus status) {
        requestRepository.findById(id).map(request -> {
            request.setStatus(status);
            return requestRepository.save(request);
        }).orElseThrow(() -> new RequestNotFoundException());
    }

    private List<ItemRequest> convertItems(Request request, List<ItemRequestDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRuleException("No product on order.");
        }
        return items.stream().map(dto -> {
            Long idProduct = dto.getProduct_id();
            Product product = productRepository.findById(idProduct).orElseThrow(() -> new BusinessRuleException("Invalid product code." + idProduct));

            ItemRequest itemRequest = new ItemRequest();
            itemRequest.setAmount(dto.getAmount());
            itemRequest.setRequest(request);
            itemRequest.setProduct(product);

            return itemRequest;
        }).collect(Collectors.toList());
    }
}
