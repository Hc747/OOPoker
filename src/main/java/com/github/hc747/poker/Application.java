package com.github.hc747.poker;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        final Dealer dealer = new Dealer(Dealer.parse("9C 9D 8D 7C 3C 2S KD TH 9H 8H"));
        System.out.println(dealer);
        final List<Hand> hands = dealer.deal(3, 2);
        System.out.println(hands);
    }
}
