import java.text.SimpleDateFormat;

public class File extends StorageItem{
    private String suffix;
    private String content;

    /**
     * constructor of the file class initialize suffix and content
     * @param name send to constructor of storage item
     * @param suffix
     */
    File(String name, String suffix){
        super(name);
        this.suffix = suffix;
        this.content = null;
    }

    /**
     * override the abstruct function in storage item
     * @return the size of content of the file
     */
    @Override
    public int getSize(){
        if (this.content == null){
            return 0;
        }
        return this.content.length();
    }

    /**
     *
     * @return representation of the file name and suffix
     */
    public String getName(){
        return this.name + "." + this.suffix;
    }

    /**
     * print content of file in the request format
     */
    public void printContent(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(getName() + " Size: " + getSize() + "MB Created: " + sdf.format(this.date));
        System.out.println(this.content);
    }

    /**
     * add content to the file
     * @param contentToAdd
     */
    public void addContent(String contentToAdd){
        if(this.content == null){
            this.content = contentToAdd;
        } else {
            this.content += contentToAdd;
        }
    }

}
