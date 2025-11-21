import java.io.File;

public class FileTest{
    public static void main(String[] args) {
        File dir = new File("/home/pablito22/Programmazione/git/LAB_III/00-1) ripasso");
        if(dir.isDirectory()){
            String files[] = dir.list();
            for(String file:files){
                if(file.endsWith(".java")) System.out.println(file);
            }
        }
    }
}