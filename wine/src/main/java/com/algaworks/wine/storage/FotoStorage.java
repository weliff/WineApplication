package com.algaworks.wine.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	String salvar(MultipartFile foto);

	String getURL(String nomeFoto);

}