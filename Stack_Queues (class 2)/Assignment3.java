
import java.util.*;
import java.util.Queue;

public class Assignment3 {

    static class Program {

        String name;
        int capacity;

        public Program(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    static class Student {

        List<Program> preference = new ArrayList<>();
        String studentName;
        int rank;
        boolean alloted;

        public Student(String studentName, int rank, List<Program> preference, boolean alloted) {
            this.studentName = studentName;
            this.rank = rank;
            this.preference = preference;
            this.alloted = alloted;
        }
    }

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        List<Program> programList = new ArrayList<>();
        Map<String, List<Student>> trackStudent = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Programs");
            System.out.println("2. Add Students");
            System.out.println("3. Allocate Programs");
            System.out.println("4. Display Allocation Results");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of programs: ");
                    int numberOfPrograms = sc.nextInt();
                    sc.nextLine(); // Consume leftover newline

                    for (int i = 0; i < numberOfPrograms; i++) {
                        System.out.print("Enter the name of program " + (i + 1) + ": ");
                        String name = sc.nextLine();
                        System.out.print("Enter the capacity of program " + (i + 1) + ": ");
                        int capacity = sc.nextInt();
                        sc.nextLine(); // Consume leftover newline
                        programList.add(new Program(name, capacity));
                    }
                    System.out.println("Programs added successfully!");
                    break;

                case 2:
                    System.out.print("Enter the number of students: ");
                    int numberOfStudents = sc.nextInt();
                    sc.nextLine(); // Consume leftover newline

                    for (int i = 0; i < numberOfStudents; i++) {
                        System.out.print("Enter the name of student " + (i + 1) + ": ");
                        String name = sc.nextLine();
                        System.out.print("Enter the rank of student " + (i + 1) + ": ");
                        int rank = sc.nextInt();
                        sc.nextLine(); // Consume leftover newline

                        System.out.println("Available Programs:");
                        for (int k = 0; k < programList.size(); k++) {
                            System.out.println((k + 1) + ". " + programList.get(k).name);
                        }

                        List<Program> preferences = new ArrayList<>();
                        System.out.print("Enter the number of program preferences for " + name + ": ");
                        int numPreferences = sc.nextInt();
                        sc.nextLine(); // Consume leftover newline

                        for (int j = 0; j < numPreferences; j++) {
                            System.out.print("Choose program (1-" + programList.size() + "): ");
                            int p = sc.nextInt() - 1; // Convert to zero-based index
                            sc.nextLine(); // Consume leftover newline
                            if (p >= 0 && p < programList.size()) {
                                preferences.add(programList.get(p));
                            } else {
                                System.out.println("Invalid choice. Please try again.");
                                j--; // Retry the same preference
                            }
                        }

                        studentList.add(new Student(name, rank, preferences, false));
                    }
                    System.out.println("Students added successfully!");
                    break;

                case 3:
                    studentList.sort(Comparator.comparingInt(s -> s.rank));
                    Queue<Student> queue = new LinkedList<>(studentList);

                    while (!queue.isEmpty()) {
                        Student temp = queue.poll();
                        List<Program> preference = temp.preference;

                        for (Program p : preference) {
                            if (p.capacity > 0) {
                                p.capacity--;

                                List<Student> students = trackStudent.getOrDefault(p.name, new ArrayList<>());
                                students.add(temp);
                                trackStudent.put(p.name, students);

                                temp.alloted = true;
                                break;
                            }
                        }

                        if (!temp.alloted) {
                            List<Student> listt = trackStudent.getOrDefault("Not Alloted", new ArrayList<>());
                            listt.add(temp);
                            trackStudent.put("Not Alloted", listt);
                        }
                    }
                    System.out.println("Allocation completed!");
                    break;

                case 4:
                    for (Map.Entry<String, List<Student>> entry : trackStudent.entrySet()) {
                        String programName = entry.getKey();
                        List<Student> students = entry.getValue();
                        for (Student student : students) {
                            System.out.println(student.studentName + " has been allocated: " + programName);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

    }

}
