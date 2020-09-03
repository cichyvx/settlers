package map;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

public class GraphicsHandler {

    private final Image[] images;
    private final String[] names;

    public GraphicsHandler(){
        File[] files = null;
        files = getFile("ground").listFiles();
        files = addToArray(files, getFile("structures").listFiles());
        //File file_ground = getFile("ground");
        //File file_struct = getFile("structures");

        //File[] files = mergeArrays(file_ground, file_struct).//file.listFiles();
        names = new String[files.length];
        images = new Image[files.length];
        for(int i = 0; i < files.length; i++){
            try {
                images[i] = ImageIO.read(files[i].getAbsoluteFile());
                names[i] = files[i].getName().substring(0,files[i].getName().length()-4);
                System.out.println(names[i]);
            }
            catch (IOException igore){
                System.err.println("Input Error!");
            }
        }


    }

    private File[] addToArray(File[] array1, File[] array2){
        File[] tab = new File[array1.length + array2.length];

        for(int i = 0; i < tab.length; i++){
            if(i < array1.length) tab[i] = array1[i];
            else tab[i] = array2[i - array1.length];
        }

        return tab;
    }

    private File getFile(String path){
        URL res = getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }

    public Image getImage(String name){
        name = name.toUpperCase();
        for(int i = 0; i < images.length; i++){
            if(name.equals(names[i].toUpperCase()))
                return images[i];
        }
        return null;
    }

    public Image getImage(int index){
        return images[index];
    }

    public String getName(int index){
        return names[index];
    }

    public Image[] getImages(){
        return images;
    }

    public String[] getNames(){
        return names;
    }

}
