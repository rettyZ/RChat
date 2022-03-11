package pl.rettyz.rchat.utils;

public class ColorUtil {
    public static String fixZ(String text){
        return text.replace("&", "§").replace(">>", "»").replace("<<", "«");
    }
}