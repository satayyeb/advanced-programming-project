package model.features;

import model.improvements.ImprovementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeatureTypeTest {

    @Test
    void randomFeature() {
        assertNotNull(FeatureType.randomFeature());

    }

    @Test
    void canHaveTheImprovement() {
        boolean actual = FeatureType.doesContainImprovement(FeatureType.JUNGLE, ImprovementType.MINE);
        assertTrue(actual);
        actual = FeatureType.doesContainImprovement(FeatureType.ICE, ImprovementType.MINE);
        assertFalse(actual);
    }

}