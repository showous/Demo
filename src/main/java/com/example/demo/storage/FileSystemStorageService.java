package com.example.demo.storage;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
	 public Path rootLocation ;
	 public Path getRootLocation() {
		return rootLocation;
	}
	public void setRootLocation(Path rootLocation) {
		this.rootLocation = Paths.get(new  StorageProperties().getLocation());
	}
	@Override
	public void store(MultipartFile[] multipartfiles) {
		try {
			if (multipartfiles.length==0) {
				throw new StorageException("文件为空 ");
			}
			//Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			File fileUpload= null;
			for(MultipartFile file : multipartfiles)
			{
				 fileUpload = new File("D:\\javaee2\\demo\\FileUplaod" + "\\" + file.getOriginalFilename());
				System.out.println(fileUpload.getPath());
				if(!fileUpload.getParentFile().exists()){ //判断文件父目录是否存在
		        	fileUpload.getParentFile().mkdir();
		        }
		        file.transferTo(fileUpload); //保存文件
				System.out.println(file.getOriginalFilename());
			}
		
			
		} catch (IOException e) {
			throw new StorageException("保存失败 " , e);
		}
	}
	
	public String storeInputStream(InputStream inputStream, String fileName) throws IOException {
		Path path = this.rootLocation.resolve(fileName);
		if (Files.copy(inputStream, path) > 0) {
			//return path.toString();
			return fileName;
		} else {
			return null;
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("文件读取失败：", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("文件无效 : " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("文件无效： " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			if(!rootLocation.toFile().exists())
			  Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("初始化失败：", e);
		}
	}
}
