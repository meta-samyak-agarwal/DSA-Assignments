import java.util.*;

class Assignment1 {

    static class Employee {
        int empId;
        String name;
        String address;

        // Constructor
        public Employee(int id, String name, String address) {
            this.empId = id;
            this.name = name;
            this.address = address;
        }

        public static List<Employee> sortById(List<Employee> list) {
            List<Employee> finalList = new ArrayList<>(list);
            finalList.sort(Comparator.comparing(emp -> emp.empId));
            return finalList;
        }

        public static List<Employee> sortByName(List<Employee> list) {
            List<Employee> finalList = new ArrayList<>(list);
            finalList.sort(Comparator.comparing(emp -> emp.name));
            return finalList;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Employee employee = (Employee) obj;
            return empId == employee.empId;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(empId);
        }

        public static List<Employee> noDuplicate(List<Employee> list) {
            HashSet<Employee> set = new HashSet<>(list);
            return new ArrayList<>(set);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "zame1", "address1"));
        list.add(new Employee(2, "same2", "address2"));
        list.add(new Employee(3, "qame3", "address3"));
        list.add(new Employee(3, "lame4", "address4"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Sort by ID");
            System.out.println("2. Sort by Name");
            System.out.println("3. Remove Duplicates");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Employee> sortedById = Employee.sortById(list);
                    System.out.println("Employees sorted by ID:");
                    for (Employee emp : sortedById) {
                        System.out.println(emp.empId + " " + emp.name + " " + emp.address);
                    }
                    break;

                case 2:
                    List<Employee> sortedByName = Employee.sortByName(list);
                    System.out.println("Employees sorted by Name:");
                    for (Employee emp : sortedByName) {
                        System.out.println(emp.empId + " " + emp.name + " " + emp.address);
                    }
                    break;

                case 3:
                    List<Employee> noDuplicates = Employee.noDuplicate(list);
                    System.out.println("Employees after removing duplicates:");
                    for (Employee emp : noDuplicates) {
                        System.out.println(emp.empId + " " + emp.name + " " + emp.address);
                    }
                    break;

                case 4:
                    System.out.println("All Employees:");
                    for (Employee emp : list) {
                        System.out.println(emp.empId + " " + emp.name + " " + emp.address);
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}