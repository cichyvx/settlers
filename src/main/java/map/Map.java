package map;

import com.google.gson.*;
import conventers.AnimalConventer;
import conventers.TitleConventer;
import conventers.TitleHolder;
import creatures.AI;
import creatures.animals.Animal;
import map.ground.*;
import map.structures.PlantStructure;
import map.structures.Structure2D;
import map.structures.TreeStructure;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    private String name;
    private String autor;
    private String description;
    private final int WIDTH, HEIGHT;
    public Title[][] titles;
    public AI animals_AI;
    public final transient  GraphicsHandler graphicsHandler;

    /* EMPTY MAP
    *   @param   weight  horizontal number of fields
    *   @param   height  vertical number of fields
    *   @param  sWidth  horizontal size of a single field
    *   @param  sHeight  vertical size of a single field
    *   @param  random  true if a generator is to be used, false if the map is to be filled with one type
     */
    public Map(int width, int height, int sWidth, int sHeight, boolean random){
        WIDTH = width;
        HEIGHT = height;

        name = "empty map";
        autor = "";
        description = "map created by editor";
        titles = new Title[width][height];
        if(random){
            titles = generateMap(sWidth, sHeight);
        }
        else {
            titles = fillAllMap(sWidth, sHeight);
        }
        graphicsHandler = new GraphicsHandler();
        animals_AI = new AI(this);
        animals_AI.generateAnimals();
    }

    /* RANDOM GENERATED MAP
     *   @param   weight  horizontal number of fields
     *   @param   height  vertical number of fields
     *   @param  sWidth  horizontal size of a single field
     *   @param  sHeight  vertical size of a single field
     *   @param  random  true if a generator is to be used, false if the map is to be filled with one type
     *   @param  nRiver  number of rivers to be generated
     *   @param  nRock  number of rock to be generated
     *      (rocks can overlap each other giving the impression that it is one element)
     */
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
        animals_AI = new AI(this);
        animals_AI.generateAnimals();
    }

    /*
    *   @param  tab  the fields where rock are to be generated
    *   @param  sWidth  horizontal size of a single field
    *   @param  sHeight  vertical size of a single field
    *   @param  count  number of rock to be generated
    */

    private Title[][] generateRock(Title[][] tab, int sWidth, int sHeight, int count){

        Random r = new Random();
        final String name = new RockTitle(0,0).toString();
        final int chance = 50;
        /* number of rivers */
        for(int k = count; k != 0; k--){
            int startingX = r.nextInt(sWidth);
            int startingY = r.nextInt(sHeight);

            for(int i = 0; i < HEIGHT; i++){
                for(int j = 0; j < WIDTH; j++){
                    if(startingX == j && startingY == i)
                        tab[i][j] = new RockTitle(sWidth * j, i * sHeight);

                    if(j - 1 >= 0 )
                        if(tab[i][j-1].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWidth * j, i * sHeight);

                    if(j + 1 < sWidth)
                        if(tab[i][j+1].toString().equals(name))
                            if(r.nextInt(100) < chance)
                                tab[i][j] = new RockTitle(sWidth * j, i * sHeight);

                    if(i + 1 < tab.length)
                        if(tab[i+1][j].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWidth * j, i * sHeight);

                    if(i - 1 >= 0)
                        if(tab[i-1][j].toString().equals(name))
                            if(r.nextInt(100)<chance)
                                tab[i][j] = new RockTitle(sWidth * j, i * sHeight);
                }
            }
        }

        return tab;
    }


    /*
    *   @param  tab  the fields where rivers are to be generated
    *   @param  sWidth  horizontal size of a single field
    *   @param  sHeight  vertical size of a single field
    *   @param  count  number of river to be generated
    *   the method is used for mapping rivers
     */
    private Title[][] generateWater(Title[][] tab, int sWeight, int sHeight, int count){
        /*
        loop that creates the number of rivers specified in the parameter
         */
        for(int i = 0; i < count; i++){
            if(new Random().nextBoolean())
                tab = generateRiversFormUp(tab, sWeight, sHeight);
            else
                tab = generateRiversFromRight(tab, sWeight, sHeight);
        }

        return tab;
    }

    private Title[][] generateRiversFormUp(Title[][] tab, int sWeight, int sHeight){
        int riverWidth = new Random().nextInt(8) + 2;
        int startPoint = new Random().nextInt(WIDTH - 1) + 1;
        for(int i = 0; i < HEIGHT; i++){
            for(int j = startPoint; j < startPoint + riverWidth; j++){
                if(j + 1 >= WIDTH){
                    startPoint -= 3;
                    break;
                }
                tab[i][j] = new WaterTitle(i * sWeight, j * sHeight);
            }
            if(new Random().nextBoolean()){
                if(startPoint - 1 >= 0)
                    startPoint --;
            }
            else
            if(startPoint + 1 < WIDTH)
                startPoint ++;
        }
        return tab;
    }

    private Title[][] generateRiversFromRight(Title[][] tab, int sWeight, int sHeight){
        int riverWidth = new Random().nextInt(8) + 2;
        int startPoint = new Random().nextInt(HEIGHT - 1) + 1;

        for(int i = 0; i < WIDTH; i++){
            for(int j = startPoint; j < startPoint + riverWidth; j++){
                if(j + 1 >= HEIGHT){
                    startPoint -= 3;
                    break;
                }
                tab[j][i] = new WaterTitle(i * sWeight, j * sHeight);
            }
            if(new Random().nextBoolean()){
                if(startPoint - 1 >= 0)
                    startPoint --;
            }
            else
            if(startPoint + 1 < HEIGHT)
                startPoint ++;
        }

        return tab;
    }

    /*
     *   @param  tab  the fields where rock are to be generated
     *   @param  sWidth  horizontal size of a single field
     *   @param  sHeight  vertical size of a single field
     */


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

    /*
     *   @param  tab  the fields where rock are to be generated
     *   @param  sWidth  horizontal size of a single field
     *   @param  sHeight  vertical size of a single field
     */


    private Title[][] generateTrees(Title[][] tab, int sWeight, int sHeight){
        Random r = new Random();
        int count = 0;
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                if(tab[i][j].toString().equals("GRASS")){
                    if(r.nextInt(100) > 50) {
                        tab[i][j].addStructure(new TreeStructure());
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        return tab;
    }

    /*
     *   @param  tab  the fields where rock are to be generated
     *   @param  sWidth  horizontal size of a single field
     *   @param  sHeight  vertical size of a single field
     */


    private Title[][] generatePlants(Title[][] tab, int sWeight, int sHeight){
        Random r = new Random();
        int count = 0;
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                if(tab[i][j].toString().equals("GRASS")){
                    if(r.nextInt(100) > 50 && !tab[i][j].haveObject()) {
                        tab[i][j].addStructure(new PlantStructure());
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
        tab = generatePlants(tab, sWeight, sHeight);

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

    public String getName() {
        return name;
    }

    private File getFile(String path){
        URL res = graphicsHandler.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static File getFile2(String path){
        URL res = Map.class.getClassLoader().getResource(path);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void saveMap(Map map) throws IOException {

        File file = new File("resources\\maps");
        System.out.println(file.getAbsolutePath());
        file = new File(file.getAbsolutePath()+"/"+map.getName()+".json");
        file.createNewFile();
        try (Writer writer = new FileWriter(file,false)) {
            Gson gson = new Gson();
            TitleHolder titleHolder = new TitleHolder(map.getWIDTH(), map.getHEIGHT(), map.name, map.autor, map.description);
            for(int i = 0; i < map.getHEIGHT(); i++){
                for(int j = 0; j < map.getWIDTH(); j++){
                    titleHolder.addTitle(new TitleConventer(titles[i][j]));
                }
            }

            for(Animal animal:animals_AI.getAnimals())
                titleHolder.addAnimal(new AnimalConventer(animal));

            gson.toJson(titleHolder, writer);
        }

    }

    private void setTitles(Title[][] titles){
        this.titles = titles;
    }

    public static Map loadMap(String mapname){
        File file = new File("resources\\maps");
        for (File f : file.listFiles()){
            if(f.getName().equals(mapname+".json")){
                file = f;
                break;
            }
        }

        Gson gson = new Gson();
        Map map = null;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()+"\\"+mapname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TitleHolder titleHolder = gson.fromJson(reader, TitleHolder.class);
        int height = titleHolder.height;
        int width = titleHolder.width;
        Title[][] titles = new Title[height][width];
        List<Animal> animals = new ArrayList<>();

        int l = 0;
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                TitleConventer t = titleHolder.getTitles().get(l);
                titles[i][j] = Title.getTitle(t.getName(), t.getX(), t.getY());
                titles[i][j].addStructure(Structure2D.getStructure(t.getStructure()));
                l++;
            }
        }

        for (AnimalConventer animalConv:titleHolder.getAnimals()){
            animals.add(AnimalConventer.AgetAnimal(animalConv));
        }

        map = new Map(width, height, 25, 25, false);
        map.setName(titleHolder.name);
        map.setDescription(titleHolder.description);
        map.setAutor(titleHolder.autor);
        map.setTitles(titles);
        map.animals_AI.setAnimals(animals);


        return map;
    }

}
