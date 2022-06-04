package model;

public enum Card {
    B(0),
    P(1),
    H(2),
    S(3);
    private final int cardScore;

    private Card(int cardScore) {
        this.cardScore = cardScore;
    }

    public int getCardScore() {
        return cardScore;
    }
}
