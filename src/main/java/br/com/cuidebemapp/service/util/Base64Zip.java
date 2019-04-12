package br.com.cuidebemapp.service.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

public class Base64Zip {

    private static byte[] compress2(byte[] data) {
        try {

        	System.out.println("Original: " + data.length / 1024 + " Kb");  
     	
            // Create an output stream, and a gzip stream to wrap over.
            ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
            GZIPOutputStream gzip = new GZIPOutputStream(bos);

            // Compress the input string
            gzip.write(data);
            gzip.close();
            byte[] compressed = bos.toByteArray();
            System.out.println("Compressed: " +compressed.length);
            System.out.println("Compressed: " + compressed.length / 1024 + " Kb");  
            bos.close();

            // Convert to base64
            compressed = Base64.getEncoder().encode(compressed);
            System.out.println("Compressed64: " +compressed.length);
            System.out.println("Compressed64: " + compressed.length / 1024 + " Kb");  

            // return the newly created string
            return compressed;
        } catch(IOException e) {

            return null;
        }
    }
	

	  public static byte[] compress(byte[] data) throws IOException {  
	   Deflater deflater = new Deflater();
	   deflater.setLevel(Deflater.HUFFMAN_ONLY);
	   deflater.setInput(data);  
	   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);   
	   deflater.finish();  
	   byte[] buffer = new byte[1024];   
	   while (!deflater.finished()) {  
	    int count = deflater.deflate(buffer); // returns the generated code... index  
	    outputStream.write(buffer, 0, count);   
	   }  
	   outputStream.close();  
	   byte[] output = outputStream.toByteArray();  
	   System.out.println("Original: " + data.length / 1024 + " Kb");  
	   System.out.println("Compressed: " + output.length / 1024 + " Kb");  
	   return output;  
	  }  
	

}
