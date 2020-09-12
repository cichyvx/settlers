package map;

import com.google.gson.Gson;
import map.ground.*;
import map.structures.TreeStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;

public class Map {

    private String name;
    private String autor;
    private String description;
    private final int WIDTH, HEIGHT;
    public Title[][] titles;
    public final GraphicsHandler graphicsHandler;


    /* Emnpty map.Map */
    public Map(int weight, int height, int sWeight, int sHeight, boolean random){
        WIDTH = weight;
        HEIGHT = height;

        name = "empty map";
        autor = "";
        description = "map created by editor";
        titles = new Title[weight][height];
        if(random){
            titles = generateMap(sWeight, sHeight);
        }
        else {
            titles = fillAllMap(sWeight, sHeight);
        }
        graphicsHandler = new GraphicsHandler();
    }

    public Map(int weight, int height, int sWeight, int sHeight, boolean random, int nRiver, int nRock){
        WIDTH = weight;
        HEIGHT = height;

        name = "empty map";
        autor = "";
        description = "map created by editor";
        titles = new Title[weight][height];
        if(random){
            titles = generateMap(sWeight, sHeight, nRiver, nRock);
        }
        else {
            titles = fillAllMap(sWeight, sHeight);
        }
        graphicsHandler = new GraphicsHandler();
    }

    private Title[][] generateRock(Title[][] tab, int sWeight, int sHeight, int count){

        Random r = new Random();
        final String name = new RockTitle(0,0).toString();
        final int chance = 50;
        /* number of rivers */
        for(int k = count; k != 0; k--){
            int startingX = r.nextInt(sWeight);
            int startingY = r.nextInt(sHeight);

            for(int i = 0; i < HEIGHT; i++){
                for(int j = 0; j < WIDTH; j++){
                    if(startingX == j && startingY == i)
                        tab[i][j] = new RockTitle(sWeight * j, i * sHeight);

                    if(j - 1 >= 0 )
                        if(tab[i][j-1].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWeight * j, i * sHeight);

                    if(j + 1 < sWeight)
                        if(tab[i][j+1].toString().equals(name))
                            if(r.nextInt(100) < chance)
                                tab[i][j] = new RockTitle(sWeight * j, i * sHeight);

                    if(i + 1 < tab.length)
                        if(tab[i+1][j].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWeight * j, i * sHeight);

                    if(i - 1 >= 0)
                        if(tab[i-1][j].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWeight * j, i * sHeight);
                }
            }
        }

        return tab;
    }

    private Title[][] generateWater(Title[][] tab, int sWeight, int sHeight, int count){
        Random r = new Random();
        /* number of rivers */
        for(int k = count; k > 0; k--){

            int startingX = r.nextInt(WIDTH); // position where river is begin in top of mao
            final int size = r.nextInt(3) + 3; // vertical size of river
            int rightChance = r.nextInt(69)+ 1 ; // chance to change of direction river to right
            int leftChance;
            int next = -1;

            for(int i = 0; i < HEIGHT; i++){
                secondloop:
                for(int j = 0; j < WIDTH; j++){

                    if(j == startingX && next == -1){
                        int l;
                        for(l = j; l < j + size; l++){
                            if(l >= WIDTH) {
                                next = startingX;
                                break;
                            }
                            tab[i][l] = new WaterTitle(l * sWeight, i * sHeight);
                        }
                        next = startingX;
                        break;
                    }

                    else {
                        if(j == next){
                            //right direction
                            if(r.nextInt(100) < rightChance){
                                if(j + 1 < WIDTH){
                                    for(int l = 1; l < size + 1; l++){
                                        if(l + j >= WIDTH) break;
                                        tab[i][l + j] = new WaterTitle((l + j) * sWeight, i * sHeight);
                                    }
                                    next ++;
                                }
                            }

                            //left direction
                            else if(r.nextBoolean()){
                                if(j - size - 1 >= 0){
                                    for(int l = j  - 1; l < j - 1 + size; l++){
                                        if(l + 1 >= WIDTH) break;
                                        tab[i][l] = new WaterTitle(l * sWeight, i * sHeight);
                                    }
                                    next --;
                                }
                            }

                            //straight direction
                            else{
                                for(int l = next; l < next + size; l++){
                                    if(l + 1 >= WIDTH) break;
                                    tab[i][l] = new WaterTitle(l * sWeight, i * sHeight);
                                }
                            }
                            break ;
                        }

                            //break ;
                    }
                }
            }
        }

        return tab;

    }

    private Title[][] generateSand(Title[][] tab, int sWeight, int sHeight){

        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j  < WIDTH; j++){
                if(j + 1 < WIDTH)
                    if(tab[i][j].toString().equals("WATER") && tab[i][j + 1].toString().equals("GRASS"))
                        tab[i][j + 1] = new SandTitle((j + 1) * sWeight, i * sHeight);
                if( j - 1 >= 0)
                    if(tab[i][j].toString().equals("WATER") && tab[i][j - 1].toString().equals("GRASS"))
                        tab[i][j - 1] = new SandTitle((j - 1) * sWeight, i * sHeight);
            }
        }

        return tab;
    }

    private Title[][] generateTrees(Title[][] tab, int sWeight, int sHeight){
        Random r = new Random();
        int count = 0;
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                if(tab[i][j].toString().equals("GRASS")){
                    if(r.nextInt(100) > 80) {
                        tab[i][j].addStructure(new TreeStructure());
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        return tab;
    }

    private Title[][] generateMap(int sWeight, int sHeight){
        Random r = new Random();
        final int nRock =r.nextInt(2) + 2;
        final int nRiver = r.nextInt(3) + 1;
        Title[][] tab = fillAllMap(sWeight, sHeight, new GrassTitle(0,0).toString());

        tab = generateRock(tab, sWeight, sHeight, nRock);
        tab = generateWater(tab, sWeight, sHeight, nRiver);
        tab = generateSand(tab, sWeight, sHeight);
        tab = generateTrees(tab, sWeight, sHeight);


        return tab;
    }

    private Title[][] generateMap(int sWeight, int sHeight, final int nRiver, final int nRock){
        Title[][] tab = fillAllMap(sWeight, sHeight, new GrassTitle(0,0).toString());

        tab = generateRock(tab, sWeight, sHeight, nRock);
        tab = generateWater(tab, sWeight, sHeight, nRiver);
        tab = generateSand(tab, sWeight, sHeight);
        tab = generateTrees(tab, sWeight, sHeight);


        return tab;
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

    private Title[][] fillAllMap(int sWeight, int sHeight, String name){
        titles = new Title[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++)
                titles[i][j] = Title.getTitle(name,j * sWeight, i * sHeight);
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
