package br.com.fctecno.doacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fctecno.doacao.domain.model.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {

}
