package map;

import com.google.gson.Gson;
import map.ground.DefaultTitle;
import map.ground.GrassTitle;
import map.ground.Title;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Map {

    private String name;
    private String autor;
    private String description;
    private final int WIDTH, HEIGHT;
    public Title[][] titles;
    public final GraphicsHandler graphicsHandler;


    /* Emnpty map.Map */
    public Map(int weight, int height, int sWeight, int sHeight){
        WIDTH = weight;
        HEIGHT = height;

        name = "new map";
        autor = "";
        description = "map created by editor";
        titles = new Title[weight][height];
        titles = fillAllMap(sWeight, sHeight);
        graphicsHandler = new GraphicsHandler();

    }

    public int getWIDTH(){
        return WIDTH;
    }

    public int getHEIGHT(){
        return HEIGHT;
    }

    private Title[][] fillAllMap(int sWeight, int sHeight){
        titles = new Title[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++)
                titles[i][j] = new DefaultTitle(j * sWeight, i * sHeight);
        }
        return titles;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void saveMap(Map map){
        Gson gson = new Gson();

        URL res = getClass().getClassLoader().getResource("abc.txt");
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            gson.toJson(map, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
