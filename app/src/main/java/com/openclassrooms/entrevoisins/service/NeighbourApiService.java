package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */

public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     */

    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     */

    void deleteNeighbour(Neighbour neighbour);

    /**
     * Creates a neighbour
     */

    void createNeighbour(Neighbour neighbour);

    /**
     * Creates favorite neighbour list
     */

    List<Neighbour> getFavoriteNeighbours();

    /**
     * Change neighbour estate
     */

    void updateFavoriteNeighbour(Neighbour neighbour);
}
