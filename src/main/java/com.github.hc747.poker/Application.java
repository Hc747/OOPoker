package com.github.hc747.poker;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        final Dealer dealer = new Dealer(Dealer.build());
        final List<Hand> hands = dealer.deal(3, 5);
        System.out.println(dealer);
        System.out.println(hands);
    }
}
