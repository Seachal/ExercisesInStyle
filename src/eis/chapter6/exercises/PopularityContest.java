package eis.chapter6.exercises;

public interface PopularityContest<T> {
    void addContestant(T contestant); 
    void voteFor(T contestant);
    T getMostVoted();
}

