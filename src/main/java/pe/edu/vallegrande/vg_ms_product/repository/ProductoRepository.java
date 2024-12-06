package pe.edu.vallegrande.vg_ms_product.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.vg_ms_product.model.ProductoModel;
import reactor.core.publisher.Flux;
import org.springframework.data.r2dbc.repository.Query;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<ProductoModel, Long> {

    /**
     * Buscar productos por descripción (sin importar mayúsculas/minúsculas).
     * @param descripcion descripción del producto
     * @return Flux<ProductoModel> productos que coinciden
     */
    @Query("SELECT * FROM producto WHERE LOWER(descripcion) = LOWER(:descripcion)")
    Flux<ProductoModel> findByDescripcionIgnoreCase(String descripcion);

    /**
     * Buscar productos por estado ("activo" o "inactivo").
     * @param estado estado del producto
     * @return Flux<ProductoModel> productos que coinciden
     */
    Flux<ProductoModel> findByEstado(String estado);

    /**
 * Buscar productos cuyo precio unitario esté en un rango.
 * @param minPrecio precio mínimo
 * @param maxPrecio precio máximo
 * @return Flux<ProductoModel> productos dentro del rango de precios
 */
Flux<ProductoModel> findByPrecioUnitarioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);


    /**
     * Buscar productos cuya cantidad de paquetes sea mayor a un valor específico.
     * @param cantidad cantidad mínima de paquetes
     * @return Flux<ProductoModel> productos que cumplen la condición
     */
    Flux<ProductoModel> findByCantidadPaqueteGreaterThan(Integer cantidad);

}
