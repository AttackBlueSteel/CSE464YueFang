import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

enum SearchType{
    DFSTYPE,
    BFSTYPE
}

public class Path {
    private List<String> path = new ArrayList<>();

    public void append(String node){
        path.add(node);
    }

    public boolean isEmpty(){
        return path.isEmpty();
    }

    public List<String> getPath(){
        return path;
    }

    public int size(){
        return path.size();
    }

    public String get(int num){
        return path.get(num);
    }

    public String pop(){
        String result = path.get(path.size()-1);
        path.remove(path.size()-1);
        return result;
    }

    public static Path copyPath(Path temp){
        Path result = new Path();
        for(String o:temp.getPath()){
            result.append(o);
        }
        return result;
    }

    @Override
    public String toString() {
        Iterator<String> iterator = path.iterator();
        String s = "";
        while(iterator.hasNext()){
            String temp = iterator.next();
            if(iterator.hasNext()) {
                s = s + temp + "->";
            }
            else{
                s = s + temp;
            }
        }
        return s;
    }
}
