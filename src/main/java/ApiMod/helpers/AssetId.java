package ApiMod.helpers;



public interface AssetId {
    static String makePath(String id) {
        return "ApiMod:" + id;
    }
}
