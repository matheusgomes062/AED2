import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class Candidate {
        int id, votesFR, votesSR;
        float pctVPR, pctVSR;
    }

    List<Candidate> Candidates = new ArrayList<>();
    int nVoters, nCandidates, nVotes = 3;
    int[][] votesMatrix;
    boolean isSecondTime;

    public Main(int voters, int candidates) {
        this.nVoters = voters;
        this.nCandidates = candidates;

        for (int i = 0; i < nCandidates; i++) {
            Candidate newCandidate = new Candidate();
            newCandidate.id = i + 1;
            newCandidate.votesFR = 0;
            Candidates.add(newCandidate);
        }

        votesMatrix = new int[voters][candidates];
    }

    public void setVotesMatrixValues(Scanner scanner) {
        for (int i = 0; i < nVoters; i++) {
            for (int j = 0; j < nVotes; j++) {
                votesMatrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void getResultFirstRound() {
        int allValidVotes = 0;
        for (int i = 0; i < nVoters; i++) {
            for (Candidate candidate : Candidates) {
                if (candidate.id == votesMatrix[i][0]) {
                    allValidVotes++;
                    candidate.votesFR++;
                }
            }
        }

        boolean allCandidatesAreInvalid = true;
        for (Candidate candidate : Candidates) {
            candidate.pctVPR = (float) candidate.votesFR
                    / (float) allValidVotes;
            if (candidate.pctVPR >= 0.5) {
                System.out.print(candidate.id + " ");
                System.out.printf("%.2f", candidate.pctVPR * 100);
                return;
            }
            if (candidate.pctVPR > 0) {
                allCandidatesAreInvalid = false;
            }
        }

        if (allCandidatesAreInvalid) {
            System.out.print(0);
            return;
        }


        List<Candidate> candidatesOrdenated = selectionSort(Candidates, isSecondTime = false);

        Candidate cand1_ = candidatesOrdenated.get(candidatesOrdenated.size() - 1);
        Candidate cand2_ = candidatesOrdenated.get(candidatesOrdenated.size() - 2);

        if(cand1_.votesFR == cand2_.votesFR){
            System.out.print(cand2_.id + " ");
            System.out.printf("%.2f", cand2_.pctVPR * 100);
            System.out.println();

            getResultSecondRound(cand1_, cand2_);
        }
        else{
            Candidate c = candidatesOrdenated.get(candidatesOrdenated.size()-1);
            System.out.print(c.id + " ");
            System.out.printf("%.2f", c.pctVPR * 100);
            System.out.println();

            getResultSecondRound(candidatesOrdenated.get(candidatesOrdenated.size() - 1),
                    candidatesOrdenated.get(candidatesOrdenated.size() - 2));
        }

    }

    public void getResultSecondRound(Candidate candidate1, Candidate candidate2) {
        int allValidVotes = nVoters;


        for (int i = 0; i < nVoters; i++) {
            boolean noVote = true;
            for (int j = 0; j < nVotes; j++) {
                if (candidate2.id == votesMatrix[i][j]) {
                    candidate2.votesSR++;
                    noVote = false;
                    break;
                } else if (candidate1.id == votesMatrix[i][j]) {
                    candidate1.votesSR++;
                    noVote = false;
                    break;
                }
            }
            if (noVote) {
                allValidVotes--;
            }
        }

        List<Candidate> cMostVoted = new ArrayList<>();
        cMostVoted.add(candidate1);
        cMostVoted.add(candidate2);

        for (Candidate candidate : cMostVoted) {
            candidate.pctVSR = (float) candidate.votesSR
                    / (float) (allValidVotes);
        }

        cMostVoted = selectionSort(cMostVoted, isSecondTime = true);

        if(candidate1.pctVSR == candidate2.pctVSR){
            Candidate cand;
            if(candidate1.id < candidate2.id){
                cand = candidate1;
            }else{
                cand = candidate2;
            }
            System.out.print(cand.id + " ");
            System.out.printf("%.2f", cand.pctVSR * 100);
            System.out.println();

            return;
        }

        int size = cMostVoted.size() - 1;

        Candidate c = cMostVoted.get(size);
        System.out.print(c.id + " ");
        System.out.printf("%.2f", c.pctVSR * 100);
        System.out.println();

    }

    public List<Candidate> selectionSort(List<Candidate> candidates, boolean isSecondTime) {
        for (int i = 0; i < candidates.size(); i++) {
            int less = i;
            for (int j = i + 1; j < candidates.size(); j++) {
                Candidate cNow = candidates.get(j);
                Candidate cLess = candidates.get(less);
                if (cNow.votesFR < cLess.votesFR && !isSecondTime) {
                    less = j;
                }
                else if (cNow.pctVSR < cLess.pctVSR && isSecondTime) {
                    less = j;
                }
            }

            Candidate aux = candidates.get(less);
            candidates.set(less, candidates.get(i));
            candidates.set(i, aux);
        }
        return candidates;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int voters = scanner.nextInt();
        int candidates = scanner.nextInt();

        Main main = new Main(voters, candidates);

        main.setVotesMatrixValues(scanner);
        main.getResultFirstRound();

        scanner.close();
    }
}