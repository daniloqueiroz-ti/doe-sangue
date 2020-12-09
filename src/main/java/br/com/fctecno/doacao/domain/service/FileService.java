package br.com.fctecno.doacao.domain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fctecno.doacao.api.model.DataResponse;
import br.com.fctecno.doacao.api.model.DoacaoInput;
import br.com.fctecno.doacao.domain.exception.FileException;
import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.model.File;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;
import br.com.fctecno.doacao.domain.repository.FileRepository;

@Service
public class FileService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoadorRepository doadorRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	public FileService(br.com.fctecno.doacao.domain.model.File file) {
		this.fileStorageLocation = Paths.get(file.getUploadDir())
				.toAbsolutePath().normalize();		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileException("Diretório não encontrado para upload do arquivo", e);
		}
	}
	
	public DataResponse storeFile(MultipartFile file) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileException("Sorry! Filename contains invalida path sequence " + fileName);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			File f = new File();
			f.setUploadDir(targetLocation.toString());
			f.setFileName(fileName);
			
			File saved = fileRepository.save(f);
			
			return this.processaArquivo(file.getInputStream(), saved);
			
			
		} catch (Exception e) {
			throw new FileException("Could not store file " + fileName + ". Please try again!", e);
		}
		
	}
	
	public DataResponse processaArquivo(InputStream data, File file) throws JSONException {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data, StandardCharsets.UTF_8));
		
		String text = bufferedReader
			        .lines()
			        .collect(Collectors.joining("\n"));
	    
		
		for (DoacaoInput doador : jsonToDoador(text)) {
//			System.out.println(toModel(doador).toString());
			Doador d = toModel(doador);
			d.setFile(file);
			d.setData();
			doadorRepository.save(d);
		}
	   	   
		return new DataResponse();
	}
	
	public static List<DoacaoInput> jsonToDoador(String json) {
		ObjectMapper objMapper = new ObjectMapper();
		
		try {
			return objMapper.readValue(
					json, 
					objMapper.getTypeFactory().constructCollectionType(List.class, DoacaoInput.class));
		} catch (IOException e) {
            e.printStackTrace();
        }
		return new ArrayList<DoacaoInput>();
	}
	
	private Doador toModel(DoacaoInput doador) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doador, Doador.class);
	}
	
			
}
