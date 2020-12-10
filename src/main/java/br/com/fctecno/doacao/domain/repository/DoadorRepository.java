package br.com.fctecno.doacao.domain.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.model.Response;
import br.com.fctecno.doacao.domain.model.TipoSanguineo;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {

	@Query("SELECT new br.com.fctecno.doacao.domain.model.Response(e.estado, COUNT(d.id)) FROM Doador AS d JOIN Endereco AS e ON e = d.endereco GROUP BY e.estado")
	List<Response> qtdeDoadorByEstado();

	@Query("SELECT new br.com.fctecno.doacao.domain.model.Response(d.sexo, (COUNT(d.id)/(SELECT COUNT(dt.id) FROM Doador AS dt WHERE dt.sexo = d.sexo)*100)) FROM Doador AS d WHERE d.imc > 30 GROUP BY d.sexo")
	List<Response> percentObesosBySexo();
	
	@Query("SELECT new br.com.fctecno.doacao.domain.model.Response(d.tipoSanguineo, AVG(d.idade)) FROM Doador AS d GROUP BY d.tipoSanguineo")
	List<Response> avgIdadeByTipoSanguineo();
	
	@Query("SELECT CASE " 
			+ "WHEN d.idade BETWEEN 0 AND 10 THEN \'Até 10\' " 
			+ "WHEN d.idade BETWEEN 11 AND 20 THEN \'De 11 a 20\' "
			+ "WHEN d.idade BETWEEN 21 AND 30 THEN \'De 21 a 30\' "
			+ "WHEN d.idade BETWEEN 31 AND 40 THEN \'De 31 a 40\' "
			+ "WHEN d.idade BETWEEN 41 AND 50 THEN \'De 41 a 50\' "
			+ "WHEN d.idade BETWEEN 51 AND 60 THEN \'De 51 a 60\' "
			+ "WHEN d.idade BETWEEN 61 AND 70 THEN \'De 61 a 70\' "
			+ "WHEN d.idade BETWEEN 71 AND 80 THEN \'De 71 a 80\' "
			+ "WHEN d.idade BETWEEN 81 AND 90 THEN \'De 81 a 90\' "
			+ "WHEN d.idade BETWEEN 91 AND 100 THEN \'De 91 a 100\' "
			+ "WHEN d.idade BETWEEN 101 AND 110 THEN \'De 101 a 110\' "
			+ "END AS informacao, AVG(d.imc) as valor "
			+ "FROM Doador AS d "
			+ "GROUP BY informacao")
	List<Tuple> avgImcByFaixaIdade();
	
	@Query("SELECT new br.com.fctecno.doacao.domain.model.Response(d.tipoSanguineo, count(d.id)) from Doador d WHERE d.apto IS TRUE AND d.tipoSanguineo IN (:tipos) GROUP BY d.tipoSanguineo")
	List<Response> qtdeDoadoresByTiposSanguineos(@Param("tipos") List<TipoSanguineo> tipos);	
	
}
