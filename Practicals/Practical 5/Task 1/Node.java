public class Node {
    String ticket;
    long startTime;
    int key;
    Node next;

    public Node(int key, String ticket, long startTime) {
        this.key = key;
        this.ticket = ticket;
        this.startTime = startTime;
    }

    public String getPerson(){
        return ticket.substring(ticket.indexOf("-") + 1);
    }

    public String getEntrance(){
        return ticket.substring(ticket.indexOf("e") + 1, ticket.indexOf("-"));
    }
}
