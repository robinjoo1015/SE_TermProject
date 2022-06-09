package model;

import model.cell.Cell;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardList;
    private int playerId;
    private int playerScore;

    public Player(int id) {
        this.cardList = new ArrayList<Card>();
        this.playerId = id;
        this.playerScore = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public ArrayList<Card> getCardList() {
        return this.cardList;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
        this.playerScore += card.getCardScore();
    }

    public int getBridgeCardCount() {
        int count = 0;
        for (int i = 0; i < this.cardList.size(); i++) {
            if (this.cardList.get(i) == Card.B) {
                count += 1;
            }
        }
        return count;
    }

    public void removeBridgeCard() {
        for (int i = 0; i < this.cardList.size(); i++) {
            if (this.cardList.get(i) == Card.B) {
                this.cardList.remove(i);
                return;
            }
        }
    }

    public void addScore(int score) {
        this.playerScore += score;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }
}
