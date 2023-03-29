import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


enum SearchType{
    DFSTYPE,
    BFSTYPE
}
public class Path {
    private List path = new ArrayList();
    public void append(String node){
        path.add(node);
    }

    public boolean notempty(){
        if(path.size()==0){
            return false;
        }
        return true;
    }
    public List get_path(){
        return path;
    }

    public int size(){
        return path.size();
    }

    public String get(int num){
        return (String) path.get(num);
    }

    public String pop(){
        String result = (String) path.get(path.size()-1);
        path.remove(path.size()-1);
        return result;
    }

    public static Path copy_path(Path temp){
        Path result = new Path();
        for(Object o:temp.get_path()){
            result.append((String) o);
        }
        return result;
    }

    @Override
    public String toString() {
        Iterator iterator = path.iterator();
        String s = "";
        while(iterator.hasNext()){
            String temp = (String) iterator.next();
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
