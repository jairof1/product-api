package com.dislicores.api.b2c.app.product.repository.middle;

import com.dislicores.api.b2c.app.product.domain.middle.ListaPrecioInventarioDto;
import com.dislicores.api.b2c.app.product.model.middle.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, String> {

    @Query(nativeQuery = true, value = "SELECT inventory.bodega, row_id, inventory.peso_sku, inventory.unidad, " +
            "inventory.inventario, price.lista_precio, price.sku, price.precio, " +
            "price.ico, price.fecha_activacion, price.iva " +
            "FROM INVENTARIO_BODEGA inventory " +
            "JOIN LISTA_PRECIOS price ON inventory.row_id = price.row_id_item " +
            "WHERE (inventory.sku = :skuId AND price.lista_precio = :listaPrecio AND inventory.bodega = :bodega)")
    List<ListaPrecioInventarioDto> findListaPrecioBySkuAndWhitelabelInfo(@Param("skuId") String skuId, @Param("bodega") String bodega, @Param("listaPrecio") String listaPrecio);


    @Query(nativeQuery = true, value =
            "SELECT inventory.bodega, inventory.inventario, price.lista_precio, price.sku, price.precio, price.ico, price.iva " +
            "FROM INVENTARIO_BODEGA inventory " +
            "INNER JOIN LISTA_PRECIOS price ON inventory.row_id = price.row_id_item " +
            "WHERE (inventory.sku IN (:products) AND price.lista_precio = :listPrice AND inventory.bodega = :wirelabel)")
    List<ListaPrecioInventarioDto> findListaPrecioBySkusAndWhitelabelInfo(@Param("products") HashSet<String> products, @Param("wirelabel") String wirelabel, @Param("listPrice") String listPrice);


}
