package ApiMod.helpers;

public interface ModHelper {
    static String makePath(String path) {
        return "ApiMod/" + path;
    }
    static String makeId(String id) {
        return "ApiMod:" + id;
    }
}
