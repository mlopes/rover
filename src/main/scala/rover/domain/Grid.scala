package rover.domain

import cats.data.NonEmptyVector

case class Grid(terrain: NonEmptyVector[NonEmptyVector[Grid.Tile]]) {
  lazy val dimensions = Grid.Dimensions(terrain.head.length, terrain.length)
}

object Grid {
  enum Tile:
    case Empty extends Tile

  final case class Dimensions(width: Int, height: Int)

}
