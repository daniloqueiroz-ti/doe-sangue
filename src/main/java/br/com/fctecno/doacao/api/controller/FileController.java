package br.com.fctecno.doacao.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fctecno.doacao.api.model.DataResponse;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;
import br.com.fctecno.doacao.domain.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private DoadorRepository doadorRepo;
	
	@PostMapping("/uploadFile")
	public List<DataResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		
		fileService.storeFile(file);
		
		List<DataResponse> result = new ArrayList<>();
		
		DataResponse qtdeDoador = new DataResponse("Quantidade de candidato por estado do Brasil", doadorRepo.qtdeDoadorByEstado());
		DataResponse imcMedio = new DataResponse(doadorRepo.avgImcByFaixaIdade(), "Média de idade por tipo sanguíneo");
		DataResponse perObesos  = new DataResponse("Percentual de obesos entre homens e mulheres", doadorRepo.percentObesosBySexo());
		DataResponse avgIdadeTipo = new DataResponse("Média de idade por tipo sanguíneo", doadorRepo.avgIdadeByTipoSanguineo());
		
		result = List.of(qtdeDoador, imcMedio, perObesos, avgIdadeTipo);
		
		return result;
	}
}
