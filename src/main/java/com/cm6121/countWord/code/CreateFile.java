package com.cm6121.countWord.code;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    public File folderCreate(String folderName){
        File folder = new File(folderName);
        if(folder.mkdir()){
            System.out.println("Folder " + folder.getName()+ " is successfully created");
        }else{
            System.out.println("Folder " + folder.getName()+ " already exists");
        }
        System.out.println();
        return folder;
    }

    public String fileCreate(String fileName) {

        try {
            File fl = new File(fileName);
            if (fl.createNewFile()) {
                System.out.println("File " + fl.getName()+ " is successfully created");
            } else {
                System.out.println("File " + fl.getName()+ " already exists");
            }
        }catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        return fileName;
    }
}
