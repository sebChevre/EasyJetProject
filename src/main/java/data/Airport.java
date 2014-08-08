package data;

import java.util.Arrays;

/**
 * Created by seb on 08.08.14.
 */
public class Airport {

    String code;
    String name;

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dests=" + Arrays.toString(dests) +
                '}';
    }

    String [] dests;


}
