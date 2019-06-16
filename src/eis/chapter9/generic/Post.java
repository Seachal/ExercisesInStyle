package eis.chapter9.generic;

/**
   Posts in a social network, connected by common commenters and 
   counting all likes among connected posts.

   Implemented as a specialization of the generic
   UnionFindNode infrastructure.

   @version 1.0
   @author Marco Faella
 */
public class Post extends UnionFindNode<Integer,PostSummary> {
    public Post() {
        super(PostSummary.ops);
    }
}

class PostSummary {
    private int likeCount;
    public PostSummary(int likeCount) {
        this.likeCount = likeCount;
    }
    public PostSummary() {}
    public void update(int likes) {
        likeCount += likes;
    }
    public PostSummary merge(PostSummary summary) {
        return new PostSummary(likeCount + summary.likeCount);
    }
    public int getCount() {
        return likeCount;
    }
    public static final Attribute<Integer,PostSummary> ops =
        Attribute.of(PostSummary::new,
                     PostSummary::update,
                     PostSummary::merge,
                     PostSummary::getCount);
}


