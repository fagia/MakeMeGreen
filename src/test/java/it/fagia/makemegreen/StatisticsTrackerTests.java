package it.fagia.makemegreen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Statistics tracker")
class StatisticsTrackerTests {

    @Nested
    @DisplayName("get samples")
    class GetSamplesTests {

        @Test
        @DisplayName("should return empty array")
        void getEmptySamples() {
            final float[] expected = new float[0];

            StatisticsTracker st = new StatisticsTracker();
            float[] actual = st.getSamples();

            assertArrayEquals(expected, actual);
        }

        @Test
        @DisplayName("should return a single element array")
        void getOneSample() {
            final float[] expected = new float[]{0f};

            StatisticsTracker st = new StatisticsTracker();
            st.addSample(0f);
            float[] actual = st.getSamples();

            assertArrayEquals(expected, actual);
        }

        @Test
        @DisplayName("should return a multiple elements array")
        void getMultipleSamples() {
            final float[] expected = new float[]{1f, 2f, 4f};

            StatisticsTracker st = new StatisticsTracker();
            st.addSample(1f);
            st.addSample(2f);
            st.addSample(4f);
            float[] actual = st.getSamples();

            assertArrayEquals(expected, actual);
        }

    }
}