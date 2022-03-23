package rover.domain

case class Rover(facing: Direction, position: Position) {

  def rotate(rotationDirection: RotationDirection): Rover =
    rotationDirection match
      case RotationDirection.Clockwise => copy(facing = facing.rotateClockwise)
      case RotationDirection.AntiClockwise => copy(facing = facing.rotateAntiClockwise)

  def move(grid: Grid): Rover =
    copy(position = grid.neighbourToThe(facing, position))

}


