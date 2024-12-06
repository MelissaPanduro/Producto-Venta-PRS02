package pe.edu.vallegrande.product.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import pe.edu.vallegrande.product.model.ProductoModel;
import pe.edu.vallegrande.product.service.ProductoService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoRest {

    private final ProductoService productoService;

    /**
     * Obtener todos los productos.
     * @return Lista de productos.
     */
    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public Flux<ProductoModel> getAllProductos() {
        return productoService.getAllProductos();
    }

    /**
     * Obtener un producto por su ID.
     * @param id Identificador del producto.
     * @return Producto encontrado.
     */
    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/{id}")
    public Mono<ProductoModel> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    /**
     * Crear un nuevo producto.
     * @param producto Datos del producto a crear.
     * @return Producto creado.
     */
    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public Mono<ProductoModel> createProducto(@RequestBody ProductoModel producto) {
        return productoService.createProducto(producto);
    }

    /**
     * Actualizar un producto existente.
     * @param id Identificador del producto.
     * @param producto Nuevos datos del producto.
     * @return Producto actualizado.
     */
    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public Mono<ProductoModel> updateProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProducto(id, producto);
    }

    /**
     * Eliminar un producto de forma lógica (cambiar su estado a "inactivo").
     * @param id Identificador del producto.
     * @return Producto actualizado como "inactivo".
     */
    @Operation(summary = "Eliminar un producto de forma lógica")
    @DeleteMapping("/{id}")
    public Mono<ProductoModel> deleteLogicProducto(@PathVariable Long id) {
        return productoService.deleteLogicProducto(id);
    }

    /**
     * Restaurar un producto eliminado (cambiar su estado a "activo").
     * @param id Identificador del producto.
     * @return Producto restaurado como "activo".
     */
    @Operation(summary = "Restaurar un producto eliminado")
    @PutMapping("/restaurar/{id}")
    public Mono<ProductoModel> restoreProducto(@PathVariable Long id) {
        return productoService.restoreProducto(id);
    }

    /**
     * Buscar productos por estado (activo/inactivo).
     * @param estado Estado del producto.
     * @return Productos con el estado especificado.
     */
    @Operation(summary = "Buscar productos por estado")
    @GetMapping("/estado/{estado}")
    public Flux<ProductoModel> getProductosByEstado(@PathVariable String estado) {
        return productoService.buscarPorEstado(estado);
    }

    /**
     * Buscar productos por rango de precios.
     * @param minPrecio Precio mínimo.
     * @param maxPrecio Precio máximo.
     * @return Productos dentro del rango especificado.
     */
    @Operation(summary = "Buscar productos por rango de precios")
    @GetMapping("/precio")
    public Flux<ProductoModel> getProductosByPrecioUnitarioRange(
            @RequestParam BigDecimal minPrecio,
            @RequestParam BigDecimal maxPrecio) {
        return productoService.buscarPorRangoDePrecios(minPrecio, maxPrecio);
    }

    /**
     * Buscar productos por coincidencia parcial en el nombre.
     * @param nombre Parte del nombre del producto.
     * @return Productos que contienen el término especificado.
     */
    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/buscar/{nombre}")
    public Flux<ProductoModel> getProductosByNombre(@PathVariable String nombre) {
        return productoService.buscarPorNombre(nombre);
    }
}
