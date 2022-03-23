package rover.domain

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.TypeCheckedTripleEquals
import cats.data.NonEmptyVector


class AStartPathFinderSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  "A* path finder" should {
    "find a path" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty),
        NonEmptyVector.of(Grid.Tile.Empty, Grid.Tile.Empty, Grid.Tile.Empty)))

      AStar.getShortestPathTo(grid, Position(0, 0), List(Position(2, 2))) should ===(Some(Step(Position(0,2),2,2,Some(Step(Position(2,2),1,4,None)))))
    }

    "find a path through the mountains" in {
      val grid: Grid = Grid(NonEmptyVector.of(
        NonEmptyVector of(Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain),
        NonEmptyVector of(Grid.Tile.Mountain, Grid.Tile.Empty,    Grid.Tile.Empty,    Grid.Tile.Empty, Grid.Tile.Mountain),
        NonEmptyVector of(Grid.Tile.Mountain, Grid.Tile.Empty,    Grid.Tile.Mountain, Grid.Tile.Empty, Grid.Tile.Mountain),
        NonEmptyVector of(Grid.Tile.Mountain, Grid.Tile.Empty,    Grid.Tile.Mountain, Grid.Tile.Empty, Grid.Tile.Mountain),
        NonEmptyVector of(Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain, Grid.Tile.Mountain)))

      AStar.getShortestPathTo(grid, Position(1, 3), List(Position(3, 3))) should ===(Some(Step(Position(1,2),6,1,Some(Step(Position(1,1),5,2,Some(Step(Position(2,1),4,3,Some(Step(Position(3,1),3,4,Some(Step(Position(3,2),2,3,Some(Step(Position(3,3),1,2,None)))))))))))))
    }
  }
}
