import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

abstract class StorageItem {

    public String name;
    public final Date date;
    public int size;

    /**
     * constructor that initialize the name of
     * the storage item and set a random date
     * @param name
     */
    StorageItem(String name) {
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long ms = 0;
        try {
            Date startDate = (Date) sdf.parseObject("2017-01-01 00:00:00.000");
            Date endDate = (Date) sdf.parseObject("2021-12-31 23:59:59.000");
            long startDateMs = startDate.getTime();
            long endDateMs = endDate.getTime();
            ms = ((Math.abs(Main.rnd.nextLong()) % (endDateMs - startDateMs)) + startDateMs);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = new Date(ms);
    }

    /**
     * a get size method that we will override
     * in the file and folder classes
     */
    public abstract int getSize();

    /**
     *
     * @return the date of the storage item
     */
    public Date getDate(){
        return this.date;
    }

    /**
     *
     * @return the name of the storage item
     */
    public String getName(){
        return this.name;
    }

    /**
     * this function will be override in the folder class
     * @param field
     */
    public void printTree(SortingField field){
    }

}