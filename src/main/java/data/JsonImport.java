package data;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seb on 08.08.14.
 */
public class JsonImport {

    public static void main(String[] args) {

        displayCurrentDirectory();

        Path path = FileSystems.getDefault().getPath("ress/jsonObject.txt");


        try {
            List<String> lignes = Files.readAllLines(path);

            String jsonAsText = lignes.get(0);

            Gson gson = new Gson();
            Airport[] airports = gson.fromJson(jsonAsText,Airport[].class);


            StringBuilder cypherQuery = generateInsertCities(airports).append(generateRelationQueries(airports));
            cypherQuery = cypherQuery.deleteCharAt(cypherQuery.length()-1);

            writeCypherScriptFile(cypherQuery,"1.2");

            System.out.println(cypherQuery.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static Map convertToMap(Airport[] airports){
        Map<String,Airport> aeroports = new HashMap<>();

        for(Airport aeroport:airports){
            aeroports.put(aeroport.code,aeroport);
        }

        return aeroports;
    }


    static StringBuilder generateRelationQueries(Airport[] aeroports){
        StringBuilder query = new StringBuilder("");

        query.append("//ajout des relations \n");
        for(Airport aeroport:aeroports){

            for(String airportRelationCode:aeroport.dests){
                query.append("("+aeroport.code+")-[:DESSERT ]->("+airportRelationCode+"),\n");
            }

        }

        query.setLength(query.length()-1);
        return query;
    }

    static StringBuilder generateInsertCities(Airport[] aeroports){

        StringBuilder query = new StringBuilder("//Ajout des aéroports \n CREATE ");

        for(Airport aeroport:aeroports){

            //ajout de l'aéroport
            query.append("("+aeroport.code+":Aeroport {ville:'"+aeroport.name+"',code:'"+aeroport.code+"'}),\n");

        }

        return query;
    }

    static void writeCypherScriptFile(StringBuilder query, String graphVersion){
        Path path = FileSystems.getDefault().getPath("ress/graph_create_"+graphVersion+".txt");

        try {
            Files.write(path, query.toString().getBytes());
            System.out.println( );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayCurrentDirectory(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
    }
}
