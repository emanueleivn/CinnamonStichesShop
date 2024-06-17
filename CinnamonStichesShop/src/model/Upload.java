package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.http.Part;

public class Upload {
	 private static final String[] ALLOWED_FILE_TYPES = {"image/png", "image/jpeg", "image/jpg"};

	    public static String saveUploadedFile(Part filePart, String uploadDirectory) throws IOException {
	        String fileName = filePart.getSubmittedFileName();
	        File file = new File(uploadDirectory, fileName);
	        try (InputStream fileContent = filePart.getInputStream();
	             FileOutputStream fos = new FileOutputStream(file)) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = fileContent.read(buffer)) != -1) {
	                fos.write(buffer, 0, bytesRead);
	            }
	        }
	        return fileName;
	    }

	    public static boolean isAllowedFileType(String mimeType) {
	        for (String allowedType : ALLOWED_FILE_TYPES) {
	            if (allowedType.equals(mimeType)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public static boolean isValidMagicNumber(Part filePart) throws IOException {
	        byte[] buffer = new byte[4];
	        try (InputStream inputStream = filePart.getInputStream()) {
	            inputStream.read(buffer, 0, 4);
	        }
	        String magicNumberHex = bytesToHex(buffer).toUpperCase();

	        return magicNumberHex.startsWith("89504E47") || magicNumberHex.startsWith("FFD8FF"); 
	    }

	
	    private static String bytesToHex(byte[] bytes) {
	        StringBuilder sb = new StringBuilder();
	        for (byte b : bytes) {
	            sb.append(String.format("%02X", b));
	        }
	        return sb.toString();
	    }
}
