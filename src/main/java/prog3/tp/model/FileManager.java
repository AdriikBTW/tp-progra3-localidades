package prog3.tp.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public static void save(LocalityRedServices service, String path) {

        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(service);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static LocalityRedServices load(String path) {
        try {
            FileInputStream fos = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fos);
            LocalityRedServices service = (LocalityRedServices) in.readObject();
            in.close();
            return service;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
