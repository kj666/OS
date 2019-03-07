

public class CircularQ<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    //isEmpty

    //addToQueue
    public void addNodeToTail(E element){
        Node<E> n = new Node<>(element);

        if(size == 0){
            head = n;
            tail = n;
        }
        else{
            Node<E> tmp = tail;
            tail.next = n;
            tail = n;
            tail.next = head;
            size++;
        }
    }

    //Search with circular List
    public boolean contains(E element){
        Node<E> current = head;
        int i = 1;

        boolean flag = false;

        if(head ==null){
            System.out.println("List is empty");
        }
        else{
            do{
                if(current.key == element){
                    flag = true;
                    break;
                }
                current = current.next;
            }while(current != head);
        }
        return flag;
    }

    //RemoveFromQueue

}
