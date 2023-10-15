package hanium.aidiary.service;

import hanium.aidiary.domain.File;
import hanium.aidiary.dto.auth.FileDto;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static hanium.aidiary.handler.ErrorCode.FILE_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;


    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(File.builder()
                .origFilename(fileDto.getOrigFilename())
                .filename(fileDto.getFilename())
                .fileUrl(fileDto.getFilePath())
                .build())
                .getId();

    }

    @Transactional
    public String getFile(Long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new CustomException(FILE_NOT_FOUND));

        return file.getFileUrl();
                /*
        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;

                 */
    }


/*
    public String storeFile(MultipartFile file) {
        System.out.println("uploadPath: "+uploadPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss_");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStamp = sdf.format(timestamp);

        String fileName = timeStamp + StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }


    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

 */
}