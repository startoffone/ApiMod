package ApiMod.helpers;

public interface ModHelper {
    static String makePath(String path) {
        return "ApiMod/" + path;
    }
}
