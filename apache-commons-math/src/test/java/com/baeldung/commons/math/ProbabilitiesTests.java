package com.nklkarthi.commons.math;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.Test;

public class ProbabilitiesTests {

    @Test
    public void whenNormalDistributionSample_thenSuccess() {
        final NormalDistribution normalDistribution = new NormalDistribution(10, 3);

        System.out.println(normalDistribution.sample());
    }

}
