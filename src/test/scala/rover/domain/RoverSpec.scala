package rover.domain

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalactic.TypeCheckedTripleEquals
import cats.data.NonEmptyVector

class RoverSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

    "Rover" should {
      "be created facing a provided direction" in {
        val rover = Rover(Direction.North, Position(0, 0))

        rover.facing should ===(Direction.North)
      }

      "rotate clockwise" in {
        val rover = Rover(Direction.North, Position(0, 0))

        val eRover = rover.rotate(RotationDirection.Clockwise)
        val sRover = eRover.rotate(RotationDirection.Clockwise)
        val wRover = sRover.rotate(RotationDirection.Clockwise)
        val nRover = wRover.rotate(RotationDirection.Clockwise)

        eRover.facing should ===(Direction.East)
        sRover.facing should ===(Direction.South)
        wRover.facing should ===(Direction.West)
        nRover.facing should ===(Direction.North)
      }

      "rotate anticlockwise" in {
        val rover = Rover(Direction.North, Position(0, 0))

        val wRover = rover.rotate(RotationDirection.AntiClockwise)
        val sRover = wRover.rotate(RotationDirection.AntiClockwise)
        val eRover = sRover.rotate(RotationDirection.AntiClockwise)
        val nRover = eRover.rotate(RotationDirection.AntiClockwise)

        wRover.facing should ===(Direction.West)
        sRover.facing should ===(Direction.South)
        eRover.facing should ===(Direction.East)
        nRover.facing should ===(Direction.North)
      }

      "move one place in the grid in the direction it is facing" in {
        val grid: Grid = Grid(NonEmptyVector.of(
            NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
            NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
            NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

        val rover: Rover = Rover(Direction.North, Position(1, 1))

        rover
          .move(grid) should ===(Rover(Direction.North, Position(1, 0)))

          rover
            .rotate(RotationDirection.Clockwise)
            .move(grid) should ===(Rover(Direction.East, Position(2, 1)))

          rover
            .rotate(RotationDirection.Clockwise)
            .rotate(RotationDirection.Clockwise)
            .move(grid) should ===(Rover(Direction.South, Position(1, 2)))

          rover
            .rotate(RotationDirection.Clockwise)
            .rotate(RotationDirection.Clockwise)
            .rotate(RotationDirection.Clockwise)
            .move(grid) should ===(Rover(Direction.West, Position(0, 1)))

      }

      "appear on the other side of the grid when walking off the grid" in {
        val grid: Grid = Grid(NonEmptyVector.of(
            NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
            NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
            NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

        Rover(Direction.North, Position(1, 0)).move(grid) should ===(
          Rover(Direction.North, Position(1, 2)))

        Rover(Direction.East, Position(2, 1)).move(grid) should ===(
          Rover(Direction.East, Position(0, 1)))

        Rover(Direction.South, Position(1, 2)).move(grid) should ===(
          Rover(Direction.South, Position(1, 0)))

        Rover(Direction.West, Position(0, 1)).move(grid) should ===(
          Rover(Direction.West, Position(2, 1)))
      }


    }

}
