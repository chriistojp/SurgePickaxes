package me.christo.surgepickaxes.Handlers;

public enum UpgradeCost {
    LEVEL_1(100),
    LEVEL_2(200),
    LEVEL_3(300),
    LEVEL_4(400),
    LEVEL_5(500);

    private final int cost;

    UpgradeCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public static int getCostForLevel(int level) {
        switch (level) {
            case 1:
                return LEVEL_1.getCost();
            case 2:
                return LEVEL_2.getCost();
            case 3:
                return LEVEL_3.getCost();
            case 4:
                return LEVEL_4.getCost();
            case 5:
                return LEVEL_5.getCost();
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }
}

