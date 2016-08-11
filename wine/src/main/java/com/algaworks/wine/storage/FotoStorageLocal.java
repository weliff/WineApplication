package com.algaworks.wine.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("storage-local")
@Component
public class FotoStorageLocal implements FotoStorage, FotoReader {
	
	private Path local;
	
	public FotoStorageLocal() {
		this.local = FileSystems.getDefault().getPath(System.getenv("HOME"), ".winefotos");
		try {
			Files.createDirectories(local);
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}

	@Override
	public String salvar(MultipartFile foto) {
		String nomeFoto = foto.getOriginalFilename();
		try {
			foto.transferTo(new File(local.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + nomeFoto));
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException("Erro salvando a foto", e);
		}
		return nomeFoto;
	}

	@Override
	public String getURL(String nomeFoto) {
		return "http://localhost:8080/fotos/" + nomeFoto;
	}

	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro recuperando a foto", e);
		}
	}

}
