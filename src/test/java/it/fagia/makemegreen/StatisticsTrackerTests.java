package it.fagia.makemegreen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Nested
    @DisplayName("get mean")
    class GetMeanTests {

        @Test
        @DisplayName("should throw exception when there are no samples")
        void getForEmptySamples() {
            StatisticsTracker st = new StatisticsTracker();
            assertThrows(IllegalArgumentException.class, st::getMean);
        }

        @Test
        @DisplayName("should throw exception when there is only one sample")
        void getForOneSample() {
            StatisticsTracker st = new StatisticsTracker();
            st.addSample(3.3f);
            assertThrows(IllegalArgumentException.class, st::getMean);
        }

        @Test
        @DisplayName("should return the mean value when there are many samples")
        void getForManySamples() {
            final float expected = 18.45f;

            StatisticsTracker st = new StatisticsTracker();
            st.addSample(3.3f);
            st.addSample(23.9f);
            st.addSample(13.6f);
            st.addSample(33f);
            float actual = st.getMean();

            assertEquals(expected, actual);
        }

    }

    @Nested
    @DisplayName("get median")
    class GetMedianTests {

        @Test
        @DisplayName("should throw exception there are no samples")
        void getForEmptySamples() {
            StatisticsTracker st = new StatisticsTracker();
            assertThrows(IllegalArgumentException.class, st::getMedian);
        }

        @Test
        @DisplayName("should throw exception when there is only one sample")
        void getForOneSample() {
            StatisticsTracker st = new StatisticsTracker();
            st.addSample(3.3f);
            assertThrows(IllegalArgumentException.class, st::getMedian);
        }

        @Nested
        @DisplayName("for even samples")
        class ForEvenSamplesTests {

            @Test
            @DisplayName("should return the mean value between the two samples at the center of the sorted samples")
            void getForEvenSamples() {
                final float expected = 18.75f;

                StatisticsTracker st = new StatisticsTracker();
                st.addSample(3.3f);
                st.addSample(23.9f);
                st.addSample(13.6f);
                st.addSample(33f);
                float actual = st.getMedian();

                assertEquals(expected, actual);
            }

        }

        @Nested
        @DisplayName("for odd samples")
        class ForOddSamplesTests {

            @Test
            @DisplayName("should return the sample value at the center of the sorted samples")
            void getForOddSamples() {
                final float expected = 23.9f;

                StatisticsTracker st = new StatisticsTracker();
                st.addSample(3.3f);
                st.addSample(23.9f);
                st.addSample(13.6f);
                st.addSample(33f);
                st.addSample(35.7f);
                float actual = st.getMedian();

                assertEquals(expected, actual);
            }

        }

    }
}