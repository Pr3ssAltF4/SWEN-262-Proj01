package R2;

public class IdGenorator {
    private static IdGenorator instance = null;
    int next = 0;
    protected IdGenorator(){}
    public static IdGenorator getInstance(){
        if(instance == null){
            instance = new IdGenorator();
        }
        return instance;
    }

    public int getNewId(){
        next++;
        return (next - 1);
    }
}
