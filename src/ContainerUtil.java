import java.io.*;

class ContainerUtil {
    public static <T> void saveContainer(CustomContainer<T> container, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(container);
            System.out.println("Container saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving container: " + e.getMessage());
        }
    }

    public static <T> CustomContainer<T> loadContainer(String filename) {
        CustomContainer<T> container = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            container = (CustomContainer<T>) ois.readObject();
            System.out.println("Container loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading container: " + e.getMessage());
        }
        return container != null ? container : new CustomContainer<>();
    }
}
