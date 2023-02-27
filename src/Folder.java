import java.util.*;

/**
 * counter is used in get size method
 * level is used in print tree method
 */
public class Folder extends StorageItem {
    private ArrayList<StorageItem> items;
    public static int counter = 0;
    public static int level = 0;

    /**
     * constructor, initialize the items array list
     * @param name send to constructor of storage item
     */
    Folder(String name) {
        super(name);
        this.items = new ArrayList<>();
    }

    /**
     * this is a recursive method, we go over the items
     * in the array list and if we find a file we increase
     * our counter if we find a folder we "get inside"
     * @return the counter itself
     */
    @Override
    public int getSize() {
        for (StorageItem item : this.items) {
            if (item instanceof File) {
                counter += ((File) item).getSize();
            } else {
                if(((Folder) item).items.size() == 0){
                    continue;
                }
                ((Folder) item).getSize();
            }
        }
        return counter;
    }

    /**
     *
     * @return the items array list
     */
    public ArrayList<StorageItem> getItems(){
        return this.items;
    }

    /**
     * check if a storage item name is already in this array list
     * @param item
     * @return true if found and false if otherwise
     */
    public boolean isExistItem(StorageItem item) {
        if(this.items == null){
            return false;
        }
        for(StorageItem substance : this.items){
            if(item instanceof File){
                if(item.getName().equals(substance.getName())){
                    return true;
                }
            } else {
                if(item instanceof Folder){
                    if(item.name.equals(substance.name)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param item to add
     * @return true if was added to this array list false otherwise
     */

    public boolean addItem(StorageItem item) {
        if (isExistItem(item)){
            return false;
        }else {
            this.items.add(item);
            return true;
        }
    }

    /**
     *
     * @param name of the folder
     * @return the folder if her name equals to
     * the given name if not return null
     */
    public Folder isExistFolderName(String name) {
        for (StorageItem item : this.items){
            if(item instanceof Folder) {
                if (((Folder) item).getName().equals(name)) {
                    return ((Folder) item);
                }
            }
        }
        return null;
    }

    /**
     *
     * @param name of the file
     * @return the file if her name equals to
     * the given name if not return null
     */
    public File isExistFileName(String name) {
        for (StorageItem item : this.items) {
            if (item instanceof File) {
                if (((File) item).getName().equals(name)) {
                    return ((File) item);
                }
            }
        }
        return null;
    }

    /**
     * splitting the path to an array, recursively going over
     * all the possibilities according to the path given
     * @param path
     * @return the file if found and null otherwise
     */
    public File findFile(String path){
        String[] arrPath = path.split("/");

        if(arrPath.length == 1 && isExistFileName(arrPath[0]) != null) {
            return isExistFileName(arrPath[0]);
        }
        if(arrPath.length == 1 && isExistFileName(arrPath[0]) == null){
            return null;
        }
        if (isExistFolderName(arrPath[0]) != null){
            return isExistFolderName(arrPath[0]).findFile(String.join("/" , Arrays.copyOfRange(arrPath, 1, arrPath.length)));
        }
        return null;
    }

    /**
     * using the comparator and the  then comparing to sort one folder according to the field given
     * @param item to sort
     * @param field to sort by
     */

    public void sorting(StorageItem item , SortingField field){
        switch (field){
            case NAME:{
                Comparator<StorageItem> compareByName = Comparator.comparing(StorageItem::getName);
                Collections.sort(((Folder) item).getItems(),compareByName);
                break;
            }
            case SIZE:{
                Comparator<StorageItem> compareBySize = Comparator.comparing(StorageItem::getSize).thenComparing(StorageItem::getName);
                Collections.sort(((Folder) item).getItems(), compareBySize);
                break;
            }
            case DATE:{
                Comparator<StorageItem> compareByDate = Comparator.comparing(StorageItem::getDate).thenComparing(StorageItem::getName);
                Collections.sort(((Folder) item).getItems(), compareByDate);
                break;
            }
            default:
        }
    }

    /**
     * recursively sorting all of the items according to the filed given
     * @param item to sort all of
     * @param field to sort by
     */
    public void sortAll(StorageItem item, SortingField field){
        if (item instanceof File){
            return;
        }
        sorting(item, field);
        for (StorageItem substance : ((Folder) item).getItems()){
            sortAll(substance, field);
        }
    }

    /**
     * sorting all of the folders according to the filed
     * given and using level to print the three according to the task
     * @param field to sort by
     */
    @Override
    public void printTree(SortingField field) {
        System.out.println(this.getName());
        level++;
        sortAll(this, field);
        for (StorageItem item: this.items) {
            for (int i = 0; i < level; i++) {
                System.out.print("|    ");
            }

            if (item instanceof File) {
                System.out.println(((File) item).getName());
            }
            if (item instanceof Folder) {
                ((Folder) item).printTree(field);
            }
        }
        level--;
    }
}
