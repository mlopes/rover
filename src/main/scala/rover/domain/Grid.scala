package rover.domain

import cats.data.NonEmptyVector

case class Grid(terrain: NonEmptyVector[NonEmptyVector[Grid.Tile]]) {
  lazy val dimensions = Grid.Dimensions(terrain.head.length, terrain.length)

  def neighbourToThe(direction: Direction, position: Position): Position =
    direction match
      case Direction.North => position.copy(y = if ( position.y <= 0) { dimensions.height - 1 } else { position.y - 1 })
      case Direction.East => position.copy(x = if ( position.x >= dimensions.width - 1) { 0 } else { position.x + 1 })
      case Direction.South => position.copy(y = if ( position.y >= dimensions.height - 1) { 0 } else { position.y + 1 })
      case Direction.West => position.copy(x = if ( position.x <= 0) { dimensions.width - 1 } else { position.x - 1 })

  def neighbours(position: Position): Map[Direction, Position] = Map(
    Direction.North -> neighbourToThe(Direction.North, position),
    Direction.East -> neighbourToThe(Direction.East, position),
    Direction.South -> neighbourToThe(Direction.South, position),
    Direction.West -> neighbourToThe(Direction.West, position))

  def at(position: Position): Option[Grid.Tile] =
    terrain.get(position.y).flatMap(_.get(position.x))
}

object Grid {
  enum Tile:
    case Empty extends Tile
    case Mountain extends Tile

  final case class Dimensions(width: Int, height: Int)

}
