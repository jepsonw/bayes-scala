package dk.bayes.clustergraph

import ClusterGraph._
import factor._
import dk.bayes.clustergraph.factor.Var
import dk.bayes.clustergraph.factor.Factor

/**
 * Represents Bayesian Network (or more generally Probabilistic Graphical Model) as a cluster graph.
 * 'Daphne Koller, Nir Friedman. Probabilistic Graphical Models, Principles and Techniques, 2009'
 *
 * @author Daniel Korzekwa
 */
trait ClusterGraph {
   
   /**
   * Adds cluster to this cluster graph.
   *
   * @param clusterId Unique cluster id
   *
   * @param clusterTypeId Unique id of a cluster type.
   * Clusters of the same cluster type usually share the same cluster initial potentials. TypeId can be used for learning
   * parameters in dynamic bayesian networks, with multiple prior, transition and emission parameters.
   *
   * @param factor Initial cluster potentials
   */
  def addCluster(clusterId: Int, factor: Factor, clusterTypeId: Option[Int]=None):Unit

  /**
   * Adds edge between clusters in this cluster graph.
   */
  def addEdge(clusterId1: Int, clusterId2: Int):Unit

  /**
   * Adds edges between clusters in this cluster graph.
   *
   * @param firstEdge Tuple2[clusterId, clusterId2]
   * @param nextEdges Tuple2[clusterId, clusterId2]
   */
  def addEdges(firstEdge: Tuple2[Int, Int], nextEdges: Tuple2[Int, Int]*):Unit

  /**
   * Returns all clusters in this cluster graph.
   */
  def getClusters(): Seq[Cluster]

  /**
   * Returns cluster for a cluster id.
   */
  def getCluster(clusterId: Int): Cluster
  
  /**
   * Returns unique variables in this cluster graph.
   */
  def getVariables():Seq[Var]
}

object ClusterGraph {

  /**
   * Creates default cluster graph.
   */
  def apply(): ClusterGraph = GenericClusterGraph()
  
}