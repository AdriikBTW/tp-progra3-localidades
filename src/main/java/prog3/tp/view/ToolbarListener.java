package prog3.tp.view;

interface ToolbarListener {
    void onLocalityAdded(String name, double latitude, double longitude);
    void onConnectionsGenerated(double kilometerCost, double percentageCost, double provinceCost);
}
