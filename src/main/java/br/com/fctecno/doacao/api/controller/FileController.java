package br.com.fctecno.doacao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fctecno.doacao.api.model.DataResponse;
import br.com.fctecno.doacao.domain.model.File;
import br.com.fctecno.doacao.domain.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/uploadFile")
	public DataResponse uploadFile(@RequestParam("file") MultipartFile file) {
		return fileService.storeFile(file);
		
	}
}
