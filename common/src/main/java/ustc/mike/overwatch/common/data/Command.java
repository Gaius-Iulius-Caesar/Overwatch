
package ustc.mike.overwatch.common.data;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;


public class Command extends Data{
    
    private int type;
    
    private HashMap<String, String> contents;
    

    public int getType() {
        return type;
    }
    

    public void setType(int type) {
        this.type = type;
    }
    

    public HashMap<String, String> getContents() {
        return contents;
    }
    

    public void setContents(HashMap<String, String> contents) {
        this.contents = contents;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Command command = (Command) o;
        
        if (type != command.type) return false;
        return contents != null ? contents.equals(command.contents) : command.contents == null;
    }
    
    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
