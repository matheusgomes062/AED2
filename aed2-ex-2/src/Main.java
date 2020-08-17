import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public class Participant {
        int id, age, firstPosition, lastPosition;
        String name;

        public void set(int i, Participant participant) {
        }
    }

    int positionToPrint, numberOfPrints, numberOfParticipants;
    List<Participant> participants = new ArrayList<>();

    public void setParticipants() {
        Scanner s = new Scanner(System.in);
        this.numberOfParticipants = s.nextInt();
        if(this.numberOfParticipants < 2 || this.numberOfParticipants > 105000) return;

        for (int i = 0; i < numberOfParticipants; i++) {
            Participant p = new Participant();
            p.id = i;
            p.firstPosition = i;
            p.lastPosition = -1;
            p.name = s.next();
            if(p.name.length() > 15) return;
            p.age = s.nextInt();
            if(p.age % 1 != 0) return;
            this.participants.add(p);
        }

        this.positionToPrint = s.nextInt();
        if(this.positionToPrint < 1 || this.positionToPrint > numberOfParticipants) return;
        this.numberOfPrints = s.nextInt();
        if(this.numberOfPrints < 1 || this.numberOfPrints > this.numberOfParticipants - this.positionToPrint+1) return;

        s.close();

        this.quicksortMedian3(this.participants, 0, this.participants.size() - 1);
        this.isStable();
    }

    public void quicksortMedian3(List<Participant> user, int start, int end) {
        if (start < end) {
            int q = this.partition(user, start, end);
            quicksortMedian3(user, start, q - 1);
            quicksortMedian3(user, q + 1, end);
        }
    }

    public int partition(List<Participant> user, int start, int end) {
        int middle = (start + end) / 2;
        Participant a = user.get(start);
        Participant b = user.get(middle);
        Participant c = user.get(end);
        int medianIndex;

        if (a.age < b.age) {
            if (b.age < c.age) medianIndex = middle;
            else medianIndex =  a.age < c.age ? end : start;
        }
        else {
            if (c.age < b.age) medianIndex = middle;
            else medianIndex =  c.age < a.age ? end : start;
        }
        this.swap(user, medianIndex, end);

        // Algortimo de partição de CORMEN
        Participant pivot = user.get(end);
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if (user.get(j).age <= pivot.age) {
                i = i + 1;
                this.swap(user, i, j);
            }
        }
        this.swap(user, i + 1, end);
        return i + 1;
    }

    public void swap(List<Participant> user, int i, int j) {
        Participant userTemp = user.get(i);
        Participant userI = user.get(i);
        Participant userJ = user.get(j);

        userTemp.set(i, userI);
        user.set(i, userJ);
        userI.lastPosition = i;
        user.set(j, userTemp);
        userJ.lastPosition = j;
    }

    public void isStable() {
        boolean stable = this.verifyOrder();
        if (stable) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public boolean verifyOrder() {
        for (int i = 0; i < this.participants.size() - 1; i++) {
            Participant p1 = participants.get(i);
            Participant p2 = participants.get(i + 1);

            if (p1.age == p2.age) {
                if ((p1.firstPosition > p2.firstPosition) || (p1.lastPosition > p2.lastPosition)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printParticipants() {
        for (int i = 0; i < this.numberOfPrints; i++) {
            Participant p = participants.get(i + positionToPrint - 1);
            System.out.println(p.name + " " + p.age);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setParticipants();
        main.printParticipants();
    }
}