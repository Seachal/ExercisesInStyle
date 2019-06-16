package eis.chapter6.exercises;

public interface TestablePopularityContest<T> {
    boolean addContestant(T contestant); 
    int voteFor(T contestant);
    int getVotes(T contestant);
    T getMostVoted();
}

