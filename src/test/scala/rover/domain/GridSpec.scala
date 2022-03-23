package rover.domain

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.TypeCheckedTripleEquals
import cats.data.NonEmptyVector


class GridSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {
  "grid" should {
    "find neighbours" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

      grid.neighbourToThe(Direction.North, Position(1, 1)) should ===(Position(1, 0))
      grid.neighbourToThe(Direction.East, Position(1, 1)) should ===(Position(2, 1))
      grid.neighbourToThe(Direction.South, Position(1, 1)) should ===(Position(1, 2))
      grid.neighbourToThe(Direction.West, Position(1, 1)) should ===(Position(0, 1))
    }

    "reapear on the other side of the grid" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

      grid.neighbourToThe(Direction.North, Position(1, 0)) should ===(Position(1, 2))
      grid.neighbourToThe(Direction.East, Position(2, 1)) should ===(Position(0, 1))
      grid.neighbourToThe(Direction.South, Position(1, 2)) should ===(Position(1, 0))
      grid.neighbourToThe(Direction.West, Position(0, 1)) should ===(Position(2, 1))
    }

    "provide neighbours to a position" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

      grid.neighbours(Position(1, 1)) should ===(Map(
        Direction.North -> Position(1, 0),
        Direction.East -> Position(2, 1),
        Direction.South -> Position(1, 2),
        Direction.West -> Position(0, 1),
        ))
    }

    "provide the tile type at a certain position" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Mountain, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

      grid.at(Position(0, 1)) should ===(Some(Grid.Tile.Empty))
      grid.at(Position(1, 1)) should ===(Some(Grid.Tile.Mountain))
    }
  }
}
