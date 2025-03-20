package pe.edu.vallegrande.vg_ms_product.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.edu.vallegrande.SupplierNPH.model.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Mono<Product> findByIdAndStatus(Long id, String status);
}
