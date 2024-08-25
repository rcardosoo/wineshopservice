package com.wineshopservice.repository;

import com.wineshopservice.domain.Compra;
import com.wineshopservice.domain.Vinho;
import com.wineshopservice.domain.projections.ClienteFielProjection;
import com.wineshopservice.domain.projections.CompraProjection;
import com.wineshopservice.domain.projections.RecomendacaoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query(value = """
           SELECT
               c.nome AS nomeCliente,
               c.cpf AS cpfCliente,
               v.tipo_vinho AS tipoVinho,
               v.preco AS preco,
               v.safra AS safra,
               v.ano_compra AS anoCompra,
               co.quantidade AS quantidade,
               (co.quantidade * v.preco) AS valorTotal
           FROM compra co
           JOIN cliente c ON (co.cliente_id = c.id)
           JOIN vinho v ON (co.vinho_codigo = v.codigo)
           ORDER BY (co.quantidade * v.preco) ASC
           """, nativeQuery = true)
    List<CompraProjection> findAllComprasOrderByValorTotal();

    @Query(value = """
           SELECT
               c.nome AS nomeCliente,
               c.cpf AS cpfCliente,
               v.tipo_vinho AS tipoVinho,
               v.preco AS preco,
               v.safra AS safra,
               v.ano_compra AS anoCompra,
               co.quantidade AS quantidade,
               (co.quantidade * v.preco) AS valorTotal
           FROM compra co
           JOIN cliente c ON (co.cliente_id = c.id)
           JOIN vinho v ON (co.vinho_codigo = v.codigo)
           WHERE v.ano_compra = :ano
           ORDER BY (co.quantidade * v.preco) DESC
           LIMIT 1
           """, nativeQuery = true)
    CompraProjection findMaiorCompraByAno(@Param("ano") int ano);

    @Query(value = """
           SELECT
               c.id AS id,
               c.nome AS nome,
               c.cpf AS cpf,
               SUM(co.quantidade * v.preco) AS valorTotalCompras,
               COUNT(co.id) AS totalCompras
           FROM compra co
           JOIN cliente c ON (co.cliente_id = c.id)
           JOIN vinho v ON (co.vinho_codigo = v.codigo)
           GROUP BY c.id, c.nome, c.cpf
           ORDER BY valorTotalCompras DESC, totalCompras DESC LIMIT 3
           """, nativeQuery = true)
    List<ClienteFielProjection> findTop3ClientesFieis();

    @Query(value = """
           SELECT
               v.tipo_vinho AS tipoVinho
           FROM compra co
           JOIN cliente c ON (co.cliente_id = c.id)
           JOIN vinho v ON (co.vinho_codigo = v.codigo)
           WHERE c.cpf = :cpf
           GROUP BY v.tipo_vinho
           ORDER BY COUNT(co.id) DESC LIMIT 1
           """, nativeQuery = true)
    RecomendacaoProjection findTipoVinhoMaisCompradoPorCliente(@Param("cpf") String cpf);
}
