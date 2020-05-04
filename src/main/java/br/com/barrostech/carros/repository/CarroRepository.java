package br.com.barrostech.carros.repository;

import br.com.barrostech.carros.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {


    List<Carro> findCarroByTipo(String tipo);
}
