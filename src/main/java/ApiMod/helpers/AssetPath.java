package ApiMod.helpers;

public interface AssetPath {
    static String makePath(String path) {
        return "ApiMod/" + path;
    }
}
