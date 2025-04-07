import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Directory {

    String name;
    LocalDateTime createdAt;
    Directory parent;
    Map<String, Directory> subDirs;

    public Directory(String name) {

        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.subDirs = new LinkedHashMap<>();
    }

    public String getPath() {

        List<String> path = new ArrayList<>();
        Directory current = this;

        while (current != null) {
            path.add(current.name);
            current = current.parent;
        }

        Collections.reverse(path);
        return String.join("\\", path);

    }

    // Display Commands

    public void printCommands() {
        System.out.println("Commands Available :");
        System.out.println("1. Make a directory (mkdir)");
        System.out.println("2. cd (change directory)");
        System.out.println("3. Move to parent directory (b)");
        System.out.println(
                "4. Display list of all folders in the current folder along with their date-time of creation. (ls)");
        System.out.println("5. Find a folder in current or subfolders  (find)");
        System.out.println("6. To display the complete directory structure (tree)");
        System.out.println("7. Exit ");
        System.out.println("8. Enter Choice");
    }
}

public class Assignment1 {

    private static Directory root = new Directory("R:");

    private static Directory current = root;

    private static void mkdir(String name) {

        if (name == null) {
            System.out.println("the name should not be null");
            return;
        }

        if (current.subDirs.containsKey(name)) {
            System.out.println("Error: Directory '" + name + "' already exists.");
        } else {
            Directory newDir = new Directory(name);
            newDir.parent = current;
            current.subDirs.put(name, newDir);
            System.out.println("Directory '" + name + "' created.");
        }

    }

    private static void cd(String name) {

        if (name == null) {
            System.out.println("Usage: cd <folder_name>");
            return;
        }

        if (current.subDirs.containsKey(name)) {
            current = current.subDirs.get(name);
        } else {
            System.out.println("Error: Directory '" + name + "' not found.");
        }
    }

    private static void back() {

        if (current.parent != null) {
            current = current.parent;
        } else {
            System.out.println("Error: Already at root directory.");
        }
    }

    private static void ls() {

        if (current.subDirs.isEmpty()) {
            System.out.println("No subdirectories.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Directory dir : current.subDirs.values()) {
                System.out.println(dir.name + " - Created at " + dir.createdAt.format(formatter));
            }
        }

        System.out.println("Total subdirectories: " + current.subDirs.size());
    }
 
    private static void tree(Directory dir, String prefix) {

        System.out.println(prefix + dir.name);

        List<Directory> children = new ArrayList<>(dir.subDirs.values());

        for (int i = 0; i < children.size(); i++) {

            Directory child = children.get(i);

            String newPrefix = prefix + (i == children.size() - 1 ? "    \u2514\u2500 " : "    \u251c\u2500 ");

            tree(child, newPrefix);

        }

    }

    public static void main(String[] args) {

        System.out.println("Welcome to Virtual Command Prompt (VCP)");
        Scanner sc = new Scanner(System.in);

        while (true) {

            current.printCommands();
            System.out.print(current.getPath() + "> ");
            String input = sc.nextLine().trim();

            if (input.isEmpty())
                continue;

            String[] parts = input.split("\\s+");
            String cmd = parts[0].toLowerCase();
            String arg = (parts.length > 1) ? parts[1] : null;

            switch (cmd) {

                case "mkdir":
                    mkdir(arg);
                    break;

                case "cd":
                    cd(arg);
                    break;

                case "bk":
                    case "back":
                        back();
                        break;

                case "ls":
                    case "list":
                        ls();
                        break;

                case "tree":
                    tree(current, "");
                    break;

                case "exit":
                    System.out.println("Exiting Virtual Command Prompt...");
                    return;

                default:
                    System.out.println("Error: Invalid command.");

            }

        }

    }

}
