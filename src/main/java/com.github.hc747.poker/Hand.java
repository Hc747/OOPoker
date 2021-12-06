package com.github.hc747.poker;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Hand {

    private final SortedSet<Card> cards;

    public Hand(Collection<Card> cards) {
        this.cards = new TreeSet<>(Objects.requireNonNull(cards, "cards"));
    }

    private Stream<Card> filter(Predicate<Card> condition) {
        return cards.stream().filter(condition);
    }

    private long count(Predicate<Card> condition) {
        return filter(condition).count();
    }

    private long count(Rank rank) {
        return count(card -> card.getRank() == rank);
    }

    private long count(Suit suit) {
        return count(card -> card.getSuit() == suit);
    }

//    enum Strength {
//        HIGHEST_CARD() {
//            @Override
//            public int strength(Hand hand) {
//                final Card card = hand.cards.last();
//                return strength(card);
//            }
//        },
//        ONE_PAIR() {
//            @Override
//            public int strength(Hand hand) {
//                final Collection<Card> cards = hand.filter(card -> hand.count(card.getRank()) == 2).collect(Collectors.toList());
//            }
//        }
//        ;
//
//        protected abstract boolean matches(Hand hand);
//    }


    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
