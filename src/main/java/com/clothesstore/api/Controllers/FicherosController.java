package com.clothesstore.api.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.clothesstore.api.upload.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@RequiredArgsConstructor
@ApiIgnore
@Api(tags = "Controlador de imagenes" , description = "Para gestionar la cargar de ficheros desde Products Controllers")
public class FicherosController {
	
	private static final Logger logger = LoggerFactory.getLogger(FicherosController.class);

    @Autowired
	private  StorageService storageService ;
	
	@GetMapping(value="/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) {
		Resource file = storageService.loadAsResource(filename);
		
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(file);
	}

}