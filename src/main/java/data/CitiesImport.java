package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by sce on 08.08.14.
 */
public class CitiesImport {

    public static void main(String[] args) {

        Document doc = null;
        try {
            System.setProperty("http.proxyHost", "proxy.ju.globaz.ch");
            System.setProperty("http.proxyPort", "8080");

            doc = Jsoup.connect("http://www.easyjet.com/en/routemap").get();

            Element content = doc.getElementById("inspireMeSearchOrigin");


            Elements links = content.getElementsByTag("option");

            int numberOfCities = 0;

            for (Element link : links) {
                String cityAbr = link.attr("id");
                String cityName = link.text();
                System.out.println(cityAbr +" "+cityName);
                numberOfCities ++;
            }

            System.out.println(numberOfCities +" founds" );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
