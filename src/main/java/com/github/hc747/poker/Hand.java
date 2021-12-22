package com.github.hc747.poker;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {

    private final SortedSet<Card> cards;

    public Hand(Collection<Card> cards) {
        this.cards = new TreeSet<>(Objects.requireNonNull(cards, "cards"));
    }

    private Stream<Card> stream() {
        return cards.stream();
    }

    private Stream<Card> filter(Predicate<Card> condition) {
        return filter(stream(), condition);
    }

    private static <T> Stream<T> filter(Stream<T> stream, Predicate<T> condition) {
        return stream.filter(condition);
    }

    private Map<Rank, List<Card>> groupByRank() {
        return group(stream(), Card::getRank);
    }

    private Map<Suit, List<Card>> groupBySuit() {
        return group(stream(), Card::getSuit);
    }

    private static <K, V> Map<K, List<V>> group(Stream<V> stream, Function<V, K> aggregator) {
        return stream.collect(Collectors.groupingBy(aggregator));
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

    private int size() {
        return cards.size();
    }

    private boolean empty() {
        return cards.isEmpty();
    }

    private Iterator<Card> iterator() {
        return cards.iterator();
    }

    public enum Strength {
        HIGHEST_CARD(1) {
            @Override
            protected Collection<Card> extract(Hand hand) {
                final Card card = hand.stream().max(Comparator.comparingInt(Card::strength)).orElseThrow(IllegalStateException::new);
                return Collections.singleton(card);
            }
        },
        ONE_PAIR(2) {
            @Override
            protected Collection<Card> extract(Hand hand) {
                return hand.groupByRank().values().stream().filter(cards -> cards.size() == 2).max(STRENGTH).orElse(null);
            }
        },
        TWO_PAIRS(4) {
            @Override
            protected Collection<Card> extract(Hand hand) {
                final Collection<List<Card>> pairs = hand.groupByRank().values().stream().filter(cards -> cards.size() == 2).collect(Collectors.toList());
                if (pairs.size() < 2) return null;
                return pairs.stream().sorted(STRENGTH.reversed()).limit(2L).flatMap(List::stream).collect(Collectors.toList());
            }
        },
        THREE_OF_A_KIND(3) {
            @Override
            protected Collection<Card> extract(Hand hand) {
                return hand.groupByRank().values().stream().filter(cards -> cards.size() == 3).max(STRENGTH).orElse(null);
            }
        },
//        STRAIGHT(5) {
//            @Override
//            protected Collection<Card> extract(Hand hand) {
//                return hand.
////                final Iterator<Card> iterator = hand.iterator();
////                Card previous = iterator.next();
////                while (iterator.hasNext()) {
////                    final Card current = iterator.next();
////                    if (previous.getRank().ordinal() != current.getRank().ordinal() - 1) {
////                        return null;
////                    }
////                    previous = current;
////                }
////                return true;
////                while
//            }
////            @Override
////            public boolean matches(Hand hand) {
//
////                if (hand.size() < 5) return false;
////                final Iterator<Card> iterator = hand.iterator();
////                Card previous = iterator.next();
////                while (iterator.hasNext()) {
////                    final Card current = iterator.next();
////                    if (previous.getRank().ordinal() != current.getRank().ordinal() - 1) {
////                        return false;
////                    }
////                    previous = current;
////                }
////                return true;
////            }
////
////            @Override
////            public int strength(Hand hand) {
////                throw new RuntimeException("Not implemented");
////            }
//        }
        ;

        private static final Collection<Strength> ALL = Collections.unmodifiableSet(EnumSet.allOf(Strength.class));
        private static final Comparator<Collection<Card>> STRENGTH = Comparator.comparingInt(Strength::of);

        private final int size;

        Strength(int size) {
            this.size = size;
        }

        protected abstract Collection<Card> extract(Hand hand);

        public boolean matches(Hand hand) {
            if (hand.size() < size) return false;
            final Collection<Card> cards = extract(hand);
            if (cards == null) return false;
            return cards.size() == size;
        }

        public int strength(Hand hand) {
            final Collection<Card> cards = extract(hand);
            if (cards == null) return -1; //TODO: constant
            return Strength.of(cards);
        }

        private static int of(Collection<Card> cards) {
            return cards.stream().mapToInt(Card::strength).sum();
        }

        private static Hand strongest(Hand... hands) {
            if (hands.length < 1) return null;
            return null;
//            if (hands.length)
//            return null;
        }

        private static Collection<Strength> elements() {
            return ALL;
        }

        public static Stream<Strength> stream() {
            return elements().stream();
        }
    }

//    private <T> Map<T, Collection<Card>> grouped(Function<Card, T> aggregator) {
//        final Map<T, Collection<Card>> groups = new HashMap<>();
//
//    }

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
