package rover.domain

trait PathFinder {
  def getShortestPathTo(grid: Grid, origin: Position, destinations: List[Position]): Option[Step]
}

case class Step(position: Position, cost: Int, estimatedCost: Int, parent: Option[Step]) {
  val score: Int = cost + estimatedCost
}

object AStar extends PathFinder {

  // I've reused an implementation of inverted A* I wrote for a vindinium
  // bot a few years ago (https://github.com/mlopes/VindiniumBot/blob/master/src/main/scala/Bots.scala)
  override def getShortestPathTo(grid: Grid, origin: Position, destinations: List[Position]): Option[Step] = {

    val destination = origin

    def loop(originalOpenTiles: List[Step], originalClosedTiles: Set[Position]): Option[Step] = {

      if(originalOpenTiles.isEmpty) {
        return None
      }

      val currentTile = originalOpenTiles.minBy(_.score)
      val closedTiles = originalClosedTiles + currentTile.position

      val newTiles: List[Step] = grid.neighbours(currentTile.position).values.toSet.collect {
        case p if p == destination =>
          Step(p, currentTile.cost + 1, estimatedCost(p, destination), Some(currentTile))
        case p if grid.at(p) == Some(Grid.Tile.Empty) =>
          Step(p, currentTile.cost + 1, estimatedCost(p, destination), Some(currentTile))
      }.toList

      val replacedOpenTiles = originalOpenTiles.map { tile =>
        newTiles.collectFirst {
          case newTile: Step if newTile.position == tile.position =>
            if (newTile.score < tile.score) {
              newTile
            } else {
              tile
            }
        } match
          case Some(t) => t
          case _ => tile
      }

      val openTiles = (newTiles.filterNot(n => replacedOpenTiles.map(_.position).contains(n.position)) ++ replacedOpenTiles).filterNot{ (s: Step) => closedTiles.contains(s.position) }

      openTiles.collectFirst {
        case Step(d, _, _, Some(p)) if d == destination =>
          p
      } match {
        case None =>
          openTiles match
            case Nil => None
            case o => loop(o, closedTiles)
        case Some(step) =>
          Some(step)
      }
    }

    loop(destinations.map {o => Step(o, 1, estimatedCost(o, destination), None)}, Set())
  }

  private def estimatedCost(origin: Position, destination: Position): Int =
    Math.abs(destination.x - origin.x) + Math.abs(destination.y - origin.y)

}
