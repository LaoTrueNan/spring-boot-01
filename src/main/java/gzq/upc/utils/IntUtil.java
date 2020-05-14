package gzq.upc.utils;


import java.util.Random;

public class IntUtil {
    public static synchronized Integer genUniqueKey(){

        Random random = new Random();
        Integer a = random.nextInt(900000)+100000;

        return a;
    }
}
