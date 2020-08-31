package map;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class GraphicsHandler {

    private final Image[] images;
    private final String[] names;

    public GraphicsHandler(){
        URL res = getClass().getClassLoader().getResource("ground");
        File file = null;
        File[] files = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        files = file.listFiles();
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
