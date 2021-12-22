package com.github.hc747.hoker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Dealer {

    private final List<Card> cards;

    public Dealer(List<Card> cards) {
        this.cards = Objects.requireNonNull(cards, "cards");
    }

    public List<Hand> deal(int nHands, int nCards) {
        if (nHands < 2) throw new IllegalArgumentException("At least two hands must be dealt.");
        if (nCards < 1) throw new IllegalArgumentException("At least one card must be dealt.");
        if (cards.size() < nHands * nCards) throw new IllegalStateException("Not enough cards to deal.");

        final List<Hand> results = new ArrayList<>(nHands);
        for (int i = 0; i < nHands; i++) {
            final List<Card> hand = new ArrayList<>(nCards);
            for (int j = 0; j < nCards; j++) {
                final Card card = cards.remove(ThreadLocalRandom.current().nextInt(cards.size()));
                hand.add(card);
            }
            results.add(new Hand(hand));
        }
        return results;
    }

    public static List<Card> build() {
        final Collection<Suit> suits = Suit.elements();
        final Collection<Rank> ranks = Rank.elements();
        final List<Card> cards = new ArrayList<>(suits.size() * ranks.size());
        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    public static List<Card> parse(String line) {
        final StringTokenizer tokenizer = new StringTokenizer(line);
        final List<Card> cards = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            if (token.length() != 2) throw new IllegalArgumentException("Input not in expected format - each token must be two characters.");
            final Rank rank = Rank.parseJava8(token.charAt(0));
            final Suit suit = Suit.parseJava8(token.charAt(1));
            cards.add(new Card(suit, rank));
        }
        return cards;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "cards=" + cards +
                '}';
    }
}
