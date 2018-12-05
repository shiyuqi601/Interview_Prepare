package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/1/18.
 */
public class FileSystem {


    Map<String, Integer> pathMap;
    Map<String, Runnable> callBackMap;
    public FileSystem() {
        this.pathMap = new HashMap<>();
        this.callBackMap = new HashMap<>();
        pathMap.put("", 0);
    }
    public boolean create(String key, int value) {
        if (pathMap.containsKey(key)) {
            return false;
        }
        String prefix = key.substring(0, key.lastIndexOf("/"));
        if (!pathMap.containsKey(prefix)) {
            return false;
        }
        pathMap.put(key, value);
        return true;
    }
    public int get(String key) {
        if (!pathMap.containsKey(key)) {
            System.out.println("Error!");
            return -1;
        }
        return pathMap.get(key);
    }
    public boolean watch(String key, String alert) {
        if (!pathMap.containsKey(key)) {
            return false;
        }
        Runnable r = new Runnable() {
            public void run() {
                System.out.println(alert);
            }
        };
        callBackMap.put(key, r);
        return true;
    }
    public boolean set(String key, int value) {
        if (!pathMap.containsKey(key)) {
            return false;
        }
        pathMap.put(key, value);
        //trigger callbacks
        String cur = key;
        while (cur.length() > 0) {
            if (callBackMap.containsKey(cur)) {
                callBackMap.get(cur).run();
            }
            cur = cur.substring(0, cur.lastIndexOf("/"));
        }
        return true;
    }
    public static void main(String args[]) {
        FileSystem solution = new FileSystem();
        solution.create("/a", 1);
        System.out.println(solution.get("/a"));
        solution.create("/a/b", 2);
        System.out.println(solution.get("/a/b"));
        solution.create("/c/d", 3);
        System.out.println(solution.get("/c"));
        solution.set("/a/b", 4);
        System.out.println(solution.get("/a/b"));
        solution.watch("/a", "/a call back triggerred");
        solution.watch("/a/b", "/a/b call back triggerred");
        solution.set("/d", 5);
        solution.create("/a/b/c", 10);
        solution.set("/a/b/c", 11);
    }
}
