class Employee{
     String name;
     int age;
     int salary;

    public Employee(String name, int age , int salary){
        this.name = name;
        this.age = age ;
        this.salary = salary;
    }
}

class Node{
    Employee e ;
    Node next;

    public Node(Employee e , Node next){
        this.e = e ;
        this.next = null;
    }
}


class Question2{

    public static Node insertionSort(Node head){
        Employee temp1 = new Employee(null, 0, 0);
        Node dummy = new Node(temp1, null);

        while(head != null){
            
            Node temp = dummy;
            while(temp.next != null  &&
             (temp.next.e.salary < head.e.salary || (temp.next.e.salary == head.e.salary  && temp.next.e.age < head.e.age))){
                temp = temp.next;
            }

            Node forward = head.next;
            head.next = temp.next;
            temp.next = head;
            head = forward;

        }

        return dummy.next;
    }


    public static void main(String[] args) {
        Node n1 = new Node(new Employee("Alice", 30, 50000), null);
        Node n2 = new Node(new Employee("Bob", 25, 60000), null);
        Node n3 = new Node(new Employee("Charlie", 35, 40000), null);
        Node n4 = new Node(new Employee("samyak", 22, 40000), null);
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        
        Node sorted = insertionSort(n1);
        
        while (sorted != null) {
            System.out.println(sorted.e.name + " " + sorted.e.salary);
            sorted = sorted.next;
        }
    }
}