package org.nrg.sandbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Sandbox {
    public static void main(String[] args) throws IOException {
        Sandbox sandbox = new Sandbox();
        sandbox.writeZip();
    }

    private void writeZip() throws IOException {
        byte[] buffer = new byte[1024];
        File zip = new File("output.zip");
        System.out.println("Writing to Zip file " + zip.getAbsolutePath());
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
        for (final File file : getFiles()) {
            System.out.println(" * Working on Zip entry: " + file.getAbsolutePath());
            ZipEntry entry = new ZipEntry(file.getName());
            out.putNextEntry(entry);
            FileInputStream in = new FileInputStream(file);

            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            in.close();
            out.closeEntry();
        }
        out.close();
        System.out.println("Done.");
    }

    private File[] getFiles() {
        return new File("/Users/rherrick/Documents/DICOM/std_20140117_113404236").listFiles();
    }
}
