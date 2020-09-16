/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.kadis;

import java.util.ArrayList;

class OrderList {
    ArrayList<OrderArticle> listArtikel = new ArrayList<>();

    public OrderList(ArrayList<OrderArticle> listArtikel) {
        this.listArtikel = listArtikel;
    }

    public ArrayList<OrderArticle> getListArtikel() {
        return listArtikel;
    }

    public void setListArtikel(ArrayList<OrderArticle> listArtikel) {
        this.listArtikel = listArtikel;
    }

    public OrderList() {
    }


}
