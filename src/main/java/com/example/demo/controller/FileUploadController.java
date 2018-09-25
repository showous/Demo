package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.storage.StorageFileNotFoundException;
import com.example.demo.storage.StorageService;

import io.swagger.annotations.Api;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@Api(tags = "FileUploadController", description = "文件上传")
public class FileUploadController {

	@Autowired
    StorageService storageService;
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public StorageService getStorageService() {
		return storageService;
	}

	@GetMapping("/listUploadedFiles")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

	@GetMapping("/FileUplaod/{filename:.+}")
	@ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

	@PostMapping("/handleFileUpload")
    public void handleFileUpload(@RequestParam("file") MultipartFile[] file,RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "上传成功" );
    
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
