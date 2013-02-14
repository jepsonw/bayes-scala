package dk.bayes.gaussian.localisation1d

import org.junit.Assert._
import org.junit.Test
import dk.bayes.gaussian.Gaussian
import dk.bayes.gaussian.LinearGaussian
import dk.bayes.gaussian.KalmanFilter

class HMMLocalisationGaussianTest {

  val priorProb = Gaussian(mu = 3, sigma = 1.5)
  val transitionProb = LinearGaussian(a = 1, b = 0, sigma = 0.2)
  val emissionProb = LinearGaussian(a = 1, b = 0, sigma = 0.9)

  @Test def single_observation {

    val location2Marginal = (priorProb * transitionProb).marginalise(0)
    val location2Posterior = (location2Marginal * emissionProb).withEvidence(1, 0.6)

    assertEquals(1.430, location2Posterior.toGaussian.mu, 0.001)
    assertEquals(0.588, location2Posterior.toGaussian.sigma, 0.001)

    assertEquals(3, location2Marginal.toGaussian.mu, 0.001)
    assertEquals(1.7, location2Marginal.toGaussian.sigma, 0.001)

  }
}