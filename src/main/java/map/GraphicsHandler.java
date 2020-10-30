package map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/*
    class using for handling in ram memory array of graphics representation of object.
    recommended quantity for this class is one
    class collecting all files from all folder in resources\graphics folder
    class can return list of all sources or one of them
 */

public class GraphicsHandler {

    private final Image[] images;
    private final String[] names;

    public GraphicsHandler(){
        final String resourceFolderName = "resources\\graphics";

        File[] toList = new File(resourceFolderName).listFiles();
        File[] files = null;

        assert toList != null;

        for(File file: toList){
            files = addToArray(files, file.listFiles());
        }

        assert files != null;


        names = new String[files.length];
        images = new Image[files.length];

        for(int i = 0; i < files.length; i++){
            try {
                images[i] = ImageIO.read(files[i].getAbsoluteFile());
                names[i] = files[i].getName().substring(0,files[i].getName().length() - 4); //deleting last 4 characters because name in Array must be the same as Object.toString who using it
                System.out.println(names[i] +" " + files[i].getAbsolutePath());
            }
            catch (IOException e){
                System.err.println("Input Error!");
            }
        }


    }

    private File[] addToArray(File[] array1, File[] array2){
        if(array1 == null && array2 == null) return null;
        else if(array1 == null) return array2;
        else if(array2 == null) return array1;
        File[] tab = new File[array1.length + array2.length];

        for(int i = 0; i < tab.length; i++){
            if(i < array1.length) tab[i] = array1[i];
            else tab[i] = array2[i - array1.length];
        }

        return tab;
    }


    /*
    use this method when you want get Image of Object by name of this object
     */
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
