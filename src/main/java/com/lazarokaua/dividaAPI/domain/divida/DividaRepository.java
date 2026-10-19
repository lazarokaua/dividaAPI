package com.lazarokaua.dividaAPI.domain.divida;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * DividaRepository
 */
public interface DividaRepository extends JpaRepository<Divida, Long>{


    @Query("SELECT d FROM Divida d WHERE d.cpfDevedor = :cpf")
    Page<Divida> findByCpfDevedor(@Param("cpf") String cpf, Pageable pageable);

}
