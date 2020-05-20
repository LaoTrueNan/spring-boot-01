package gzq.upc.utils;


import java.util.Random;

public class IntUtil {
    public static synchronized Integer getUniqueKey(){

        Random random = new Random();
        Integer a = random.nextInt(900000)+100000;

        return a;
    }
}
