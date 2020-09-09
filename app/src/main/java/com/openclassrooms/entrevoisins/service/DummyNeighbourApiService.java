package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */

public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * Get list of neighbours
     */

    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * Delete a neighbour from the list
     */

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * Create a neighbour, add it to the list
     */

    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Creates list of Favorite Neighbours
     */

    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> currentNeighbours = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour currentNeighbour = neighbours.get(i);
            if (currentNeighbour.getFavorite()) {
                currentNeighbours.add(currentNeighbour);
            }
        }
        return currentNeighbours;
    }

    /**
     * Change Neighbour estate to favorite
     */

    public void updateFavoriteNeighbour(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour currentNeighbour = neighbours.get(i);
            if (currentNeighbour.getId() == neighbour.getId()) {
                currentNeighbour.setFavorite(neighbour.getFavorite());
            }
        }
    }
}
