import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String args[]){
        // Create the voting station
        VotingStation vs = new VotingStation();

        // Create the marshals
        Marshal civilServantMarshal = new Marshal(vs);
        Marshal elderlyMarshal = new Marshal(vs);
        Marshal disabledMarshal = new Marshal(vs);
        Marshal unemployedMarshal = new Marshal(vs);
        Marshal studentMarshal = new Marshal(vs);

    
        civilServantMarshal.start();
        elderlyMarshal.start();
        disabledMarshal.start();
        unemployedMarshal.start();
        studentMarshal.start();
    }
}

/**
Thread-3 with Person-3 cast a vote.
Thread-4 with Person-4 entered the voting station.
Queue: {Thread-4 with Person-4}-> {Thread-0 with Person-4}-> {Thread-1 with Person-3}-> {Thread-2 with Person-3}-> {Thread-3 with Person-3} [Head]

Thread-2 with Person-3 cast a vote.
Thread-3 with Person-4 entered the voting station.
Queue: {Thread-3 with Person-4}-> {Thread-4 with Person-4}-> {Thread-0 with Person-4}-> {Thread-1 with Person-3}-> {Thread-2 with Person-3} [Head]

Thread-2 with Person-4 entered the voting station.
Thread-1 with Person-3 cast a vote.
Queue: {Thread-2 with Person-4}-> {Thread-3 with Person-4}-> {Thread-4 with Person-4}-> {Thread-0 with Person-4}-> {Thread-1 with Person-3} [Head]

Thread-0 with Person-4 cast a vote.
Thread-1 with Person-4 entered the voting station.
Queue: {Thread-1 with Person-4}-> {Thread-2 with Person-4}-> {Thread-3 with Person-4}-> {Thread-4 with Person-4}-> {Thread-0 with Person-4} [Head]

Thread-4 with Person-4 cast a vote.
Queue: {Thread-1 with Person-4}-> {Thread-2 with Person-4}-> {Thread-3 with Person-4}-> {Thread-4 with Person-4} [Head]

Thread-3 with Person-4 cast a vote.
Queue: {Thread-1 with Person-4}-> {Thread-2 with Person-4}-> {Thread-3 with Person-4} [Head]

Thread-2 with Person-4 cast a vote.
Queue: {Thread-1 with Person-4}-> {Thread-2 with Person-4} [Head]

Thread-1 with Person-4 cast a vote.
Queue: {Thread-1 with Person-4} [Head]
 */