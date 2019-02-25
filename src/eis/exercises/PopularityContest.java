public interface PopularityContest<T> {
    void addContestant(T contestant); 
    void vote(T contestant);
    T getMostVoted();
}

